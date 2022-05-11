package com.guava.customerms.mockdata;

import com.guava.customerms.constant.UserStatus;
import com.guava.customerms.dto.CreateCustomerRequestDto;
import com.guava.customerms.dto.CreateCustomerResponseDto;
import com.guava.customerms.dto.feign.UserRequestDto;
import com.guava.customerms.dto.feign.UserResponseDto;
import com.guava.customerms.entity.CustomerEntity;

public class CustomerMockData {

    public static final Long CUSTOMER_ID = 1L;
    public static final String USER_ID = "12";

    public CustomerEntity getCustomerEntity() {
        return CustomerEntity.builder()
                .customerId(CUSTOMER_ID)
                .userId(USER_ID)
                .firstName("lala")
                .email("lala@gmail.com").build();
    }

    public CreateCustomerRequestDto getCreateCustomerRequestDto() {
        return CreateCustomerRequestDto.builder()
                .username("lala")
                .password("password")
                .firstName("lala")
                .email("lala@gmail.com").build();
    }

    public CreateCustomerResponseDto getCreateCustomerResponseDto() {
        return CreateCustomerResponseDto.builder()
                .customerId(1L)
                .userId("12")
                .firstName("lala")
                .email("lala@gmail.com")
                .build();
    }

    public UserResponseDto getUserResponseDto() {
        return UserResponseDto.builder()
                .userid("12")
                .username("lala")
                .role("CUSTOMER")
                .build();
    }

    public UserRequestDto getUserRequestDto() {
        return UserRequestDto.builder()
                .password("password")
                .username("lala")
                .role("CUSTOMER")
                .status(UserStatus.ACTIVE).build();
    }


}
