package com.guava.deliveryms.service.impl;

import com.guava.deliveryms.client.CourierMsFeignClient;
import com.guava.deliveryms.constant.CourierStatus;
import com.guava.deliveryms.constant.OrderStatus;
import com.guava.deliveryms.constant.UserRole;
import com.guava.deliveryms.dto.feign.CourierStatusReqDto;
import com.guava.deliveryms.dto.req.OrderRequestDto;
import com.guava.deliveryms.dto.req.OrderStatusRequestDto;
import com.guava.deliveryms.dto.resp.OrderDetailsResponseDto;
import com.guava.deliveryms.dto.resp.OrderResponseDto;
import com.guava.deliveryms.entity.OrderEntity;
import com.guava.deliveryms.exception.DatNotAccessibleException;
import com.guava.deliveryms.exception.DatNotFoundException;
import com.guava.deliveryms.exception.IllegalOperationException;
import com.guava.deliveryms.mapper.OrderMapper;
import com.guava.deliveryms.repository.OrderRepo;
import com.guava.deliveryms.service.OrderService;
import com.guava.deliveryms.util.AuthorizationUtil;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final AuthorizationUtil authorizationUtil;
    private final CourierMsFeignClient courierClient;

    @Override
    public OrderDetailsResponseDto createOrder(OrderRequestDto requestDto) {

        OrderEntity entity =
                orderMapper.dtoToEntity(requestDto, authorizationUtil.getAuthorizedUserId(), OrderStatus.NEW.name());
        return orderMapper.entityToOrderDetailsDto(saveOrder(entity));
    }

    @Override
    public List<OrderResponseDto> getOrders() {

        String userId = authorizationUtil.getAuthorizedUserId();
        List<OrderEntity> orders = Collections.emptyList();

        switch (authorizationUtil.getUserProfile()) {
            case UserRole.CUSTOMER:
                orders = orderRepo.findAllByUserId(userId);
                break;
            case UserRole.COURIER:
                orders = orderRepo.findAllByCourierId(userId);
                break;
            case UserRole.ADMIN:
                orders = orderRepo.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
                break;
        }
        log.info("Order details retrieved for user: {}", userId);
        return orderMapper.entityListToOrderDtoList(orders);
    }


    @Override
    public OrderDetailsResponseDto getOrderDetails(String orderId) {
        OrderEntity orderEntity = getOrderEntityByAuthority(orderId);
        return orderMapper.entityToOrderDetailsDto(orderEntity);
    }


    @Override
    public OrderResponseDto updateOrderStatus(String orderId, OrderStatusRequestDto requestDto) {

        String userProfile = authorizationUtil.getUserProfile();
        if (requestDto.getOrderStatus().getResponsibleProfile().equals(userProfile)) {
            OrderEntity orderEntity = getOrderEntityByAuthority(orderId);
            orderEntity.setOrderStatus(requestDto.getOrderStatus().name());
            OrderResponseDto updatedOrder = orderMapper.entityToOrderDto(saveOrder(orderEntity));
            Optional.of(updatedOrder)
                    .filter(order -> order.getOrderStatus().equals(OrderStatus.DELIVERED.name()))
                    .ifPresent(order -> updateCourierStatus(
                            CourierStatusReqDto.builder()
                                    .courierUserid(order.getCourierId())
                                    .status(CourierStatus.AVAILABLE)
                                    .build()));
            return updatedOrder;
        } else {
            throw new IllegalOperationException(
                    String.format("User is not allowed change order status to %s. User id: %s.",
                            requestDto.getOrderStatus(), authorizationUtil.getAuthorizedUserId()));

        }
    }


    @Override
    public OrderDetailsResponseDto updateOrderDetails(String orderId, OrderRequestDto requestDto) {
        OrderEntity orderEntity = getOrderEntityByAuthority(orderId);
        validateOrderStatus(orderEntity.getOrderStatus());
        orderMapper.dtoToEntity(requestDto, orderEntity);
        return orderMapper.entityToOrderDetailsDto(saveOrder(orderEntity));
    }


    @Override
    public OrderResponseDto cancelOrder(String orderId) {

        OrderEntity orderEntity = getOrderEntityByAuthority(orderId);
        validateOrderStatus(orderEntity.getOrderStatus());
        orderEntity.setOrderStatus(OrderStatus.CANCELLED.name());
        return orderMapper.entityToOrderDto(saveOrder(orderEntity));
    }


    @Override
    public OrderResponseDto assignOrderToCourier(String orderId, String courierUserId) {
        OrderEntity orderEntity = Optional.of(getOrderEntityById(orderId))
                .filter(order -> OrderStatus.NEW.name().equals(order.getOrderStatus()))
                .orElseThrow(() ->
                        new IllegalOperationException(
                                String.format("Order %s already assigned to courier.", orderId)));
        orderEntity.setCourierId(courierUserId);
        orderEntity.setOrderStatus(OrderStatus.IN_PICK_UP.name());
        OrderResponseDto orderResponseDto = orderMapper.entityToOrderDto(saveOrder(orderEntity));
        updateCourierStatus(CourierStatusReqDto.builder()
                .courierUserid(orderResponseDto.getCourierId())
                .status(CourierStatus.IN_DELIVERY).build());
        return orderResponseDto;
    }


    private OrderEntity saveOrder(OrderEntity entity) {
        OrderEntity order = orderRepo.save(entity);
        log.info("Order saved. Order: {}", order);
        return order;
    }


    private OrderEntity getOrderEntityByAuthority(String orderId) {
        String userId = authorizationUtil.getAuthorizedUserId();

        OrderEntity orderEntity = Optional.of(getOrderEntityById(orderId))
                .filter(order -> {
                    String userProfile = authorizationUtil.getUserProfile();
                    return ((UserRole.CUSTOMER.equals(userProfile) && userId.equals(order.getUserId()))
                            || (UserRole.COURIER.equals(userProfile) && userId.equals(order.getCourierId()))
                            || UserRole.ADMIN.equals(userProfile));
                })
                .orElseThrow(
                        () -> new DatNotAccessibleException(
                                String.format("User %s doesnt have access to order %s", userId, orderId)));
        return orderEntity;
    }


    private OrderEntity getOrderEntityById(String orderId) {
        OrderEntity orderEntity = orderRepo.findById(orderId)
                .orElseThrow(() -> new DatNotFoundException(String.format("Order %s doesn't exist.", orderId)));
        log.info("Order retrieved: {}", orderEntity);
        return orderEntity;
    }


    private void validateOrderStatus(String orderStatus) {
        Stream.of(OrderStatus.NEW.name(), OrderStatus.IN_PICK_UP.name())
                .filter(status -> status.equals(orderStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalOperationException(
                        String.format("User is not allowed to cancel/update order in %s status ", orderStatus)));
    }

    private void updateCourierStatus(CourierStatusReqDto courierStatusReqDto) {
        courierClient.updateCourierStatus(courierStatusReqDto);
        log.info("Courier status updated. Details: {}", courierStatusReqDto);
    }


}
