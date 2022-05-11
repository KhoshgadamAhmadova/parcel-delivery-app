package com.guava.deliveryms.service;


import com.guava.deliveryms.dto.req.OrderRequestDto;
import com.guava.deliveryms.dto.req.OrderStatusRequestDto;
import com.guava.deliveryms.dto.resp.OrderDetailsResponseDto;
import com.guava.deliveryms.dto.resp.OrderResponseDto;
import java.util.List;

public interface OrderService {

    OrderDetailsResponseDto createOrder(OrderRequestDto requestDto);

    List<OrderResponseDto> getOrders();

    OrderDetailsResponseDto getOrderDetails(String orderId);

    OrderDetailsResponseDto updateOrderDetails(String orderId, OrderRequestDto requestDto);

    OrderResponseDto updateOrderStatus(String orderId, OrderStatusRequestDto requestDto);

    OrderResponseDto cancelOrder(String orderId);

    OrderResponseDto assignOrderToCourier(String orderId, String courierUserId);

}
