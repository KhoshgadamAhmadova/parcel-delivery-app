package com.guava.deliveryms.service.impl

import com.guava.deliveryms.client.CourierMsFeignClient
import com.guava.deliveryms.constant.OrderStatus
import com.guava.deliveryms.exception.IllegalOperationException
import com.guava.deliveryms.mapper.OrderMapper
import com.guava.deliveryms.repository.OrderRepo
import com.guava.deliveryms.service.OrderService
import com.guava.deliveryms.util.AuthorizationUtil
import org.springframework.data.domain.Sort
import spock.lang.Specification

class OrderServiceImplTest extends Specification {

    private OrderMapper orderMapper
    private OrderRepo orderRepo
    private AuthorizationUtil authorizationUtil
    private OrderService orderService
    private CourierMsFeignClient courierClient

    void setup() {
        orderMapper = Mock()
        orderRepo = Mock()
        authorizationUtil = Mock()
        orderService = new OrderServiceImpl(orderMapper, orderRepo, authorizationUtil, courierClient)
    }

    def "CreateOrder"() {
        given:
        def expected = MockData.getOrderDetailsResponseDto()

        when:
        def actual = orderService.createOrder(MockData.getOrderRequestDto())

        then:
        1 * authorizationUtil.getAuthorizedUserId() >> MockData.USER_ID
        1 * orderMapper.dtoToEntity(MockData.getOrderRequestDto(), MockData.USER_ID, OrderStatus.NEW.name())
                >> MockData.getOrderEntity()
        1 * orderRepo.save(MockData.getOrderEntity()) >> MockData.getOrderEntity()
        1 * orderMapper.entityToOrderDetailsDto(MockData.getOrderEntity()) >> MockData.getOrderDetailsResponseDto()
        actual == expected
    }


    def "GetOrders for authorized user "() {
        given:
        def expected = List.of(MockData.getOrderResponseDto())

        when:
        def actual = orderService.getOrders()

        then:
        1 * authorizationUtil.getAuthorizedUserId() >> MockData.USER_ID
        1 * authorizationUtil.getUserProfile() >> MockData.USER_PROFILE
        1 * orderRepo.findAllByUserId(MockData.USER_ID) >> List.of(MockData.getOrderEntity())
        1 * orderMapper.entityListToOrderDtoList(List.of(MockData.getOrderEntity())) >>
                List.of(MockData.getOrderResponseDto())
        0 * orderRepo.findAllByCourierId(MockData.COURIER_ID)
        0 * orderRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))

        expected == actual
    }

    def "UpdateOrderStatus not allowed"() {
        given:
        def exceptionMessage = String.format(
                String.format("User is not allowed change order status to %s. User id: %s.",
                        OrderStatus.DELIVERED, MockData.USER_ID))

        when:
        orderService.updateOrderStatus(MockData.USER_ID, MockData.getOrderStatusRequestDto())

        then:
        1 * authorizationUtil.getAuthorizedUserId() >> MockData.USER_ID
        def ex = thrown(IllegalOperationException)

        exceptionMessage == ex.getMessage()
    }
}
