package com.guava.deliveryms.controller;

import com.guava.deliveryms.dto.req.OrderRequestDto;
import com.guava.deliveryms.dto.req.OrderStatusRequestDto;
import com.guava.deliveryms.dto.resp.OrderDetailsResponseDto;
import com.guava.deliveryms.dto.resp.OrderResponseDto;
import com.guava.deliveryms.service.OrderService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole(@Role.CUSTOMER)")
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.createOrder(requestDto));
    }


    @PreAuthorize("hasAnyRole(@Role.CUSTOMER,@Role.ADMIN,@Role.COURIER)")
    @GetMapping
    public ResponseEntity<Object> getOrder() {
        return ResponseEntity.ok(orderService.getOrders());
    }


    @PreAuthorize("hasAnyRole(@Role.CUSTOMER,@Role.ADMIN,@Role.COURIER)")
    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderDetails(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }


    @PreAuthorize("hasAnyRole(@Role.CUSTOMER)")
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDetailsResponseDto> updateOrderDetails(@PathVariable("orderId") String orderId,
                                                                      @RequestBody @Valid OrderRequestDto requestDto) {
        return ResponseEntity.ok(orderService.updateOrderDetails(orderId, requestDto));
    }


    @PreAuthorize("hasAnyRole(@Role.CUSTOMER)")
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }


    @PreAuthorize("hasAnyRole(@Role.ADMIN,@Role.COURIER)")
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable("orderId") String orderId,
                                                              @RequestBody OrderStatusRequestDto requestDto) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, requestDto));
    }


    @PreAuthorize("hasAnyRole(@Role.ADMIN)")
    @PutMapping("{orderId}/courier")
    public ResponseEntity<OrderResponseDto> assignOrder(@PathVariable("orderId") String orderId,
                                                        @RequestParam("courier-user-id") String courierUserId) {
        return ResponseEntity.ok(orderService.assignOrderToCourier(orderId, courierUserId));
    }

}
