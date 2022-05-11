package com.guava.deliveryms.service.impl;


import com.guava.deliveryms.constant.OrderStatus;
import com.guava.deliveryms.constant.UserRole;
import com.guava.deliveryms.dto.AddressDetailsDto;
import com.guava.deliveryms.dto.req.OrderRequestDto;
import com.guava.deliveryms.dto.req.OrderStatusRequestDto;
import com.guava.deliveryms.dto.resp.OrderDetailsResponseDto;
import com.guava.deliveryms.dto.resp.OrderResponseDto;
import com.guava.deliveryms.entity.OrderEntity;

public class MockData {

    public static final String USER_ID = "232";
    public static final String COURIER_ID = "232";
    public static final String USER_PROFILE = UserRole.CUSTOMER;
    private static final String ADDRESS = "Heydar Aliyev pr.30.Apartment 12.";
    private static final String CITY = "BAKU";
    private static final String PHONE = "512223322";

    private static AddressDetailsDto getAddressDetails() {
        return AddressDetailsDto.builder().address(ADDRESS)
                .city(CITY)
                .contactPhone(PHONE)
                .build();
    }

    public static OrderRequestDto getOrderRequestDto() {

        return OrderRequestDto.builder()
                .pickupDetails(getAddressDetails())
                .deliveryDetails(getAddressDetails())
                .build();
    }

    public static OrderEntity getOrderEntity() {

        return OrderEntity.builder()
                .orderStatus(OrderStatus.NEW.name())
                .deliveryAddress(ADDRESS)
                .deliveryCity(CITY)
                .deliveryContactPhone(PHONE)
                .pickupAddress(ADDRESS)
                .pickupCity(CITY)
                .pickupContactPhone(PHONE)
                .build();
    }

    public static OrderDetailsResponseDto getOrderDetailsResponseDto() {

        return OrderDetailsResponseDto.builder()
                .orderId("1")
                .courierId(null)
                .deliveryDetails(getAddressDetails())
                .pickupDetails(getAddressDetails())
                .build();
    }


    public static OrderResponseDto getOrderResponseDto() {

        return OrderResponseDto.builder()
                .orderId("1")
                .courierId(null)
                .build();
    }


    public static OrderStatusRequestDto getOrderStatusRequestDto() {

        return OrderStatusRequestDto.builder()
                .orderStatus(OrderStatus.DELIVERED)
                .build();
    }

}
