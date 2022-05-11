package com.guava.customerms.service;

import com.guava.customerms.client.AuthMsFeignClient;
import com.guava.customerms.constant.UserStatus;
import com.guava.customerms.dto.CreateCustomerRequestDto;
import com.guava.customerms.dto.CreateCustomerResponseDto;
import com.guava.customerms.dto.feign.UserResponseDto;
import com.guava.customerms.mapper.CustomerMapper;
import com.guava.customerms.mapper.UserMapper;
import com.guava.customerms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerManagementService {

    private static final String CUSTOMER_ROLE = "CUSTOMER";
    private final CustomerRepository customerRepo;
    private final CustomerMapper customerMapper;
    private final AuthMsFeignClient authMsFeignClient;
    private final UserMapper userMapper;

    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto requestDto) {

        UserResponseDto createdUser = authMsFeignClient.createUser(
                userMapper.toUserRequestDto(requestDto, CUSTOMER_ROLE, UserStatus.ACTIVE));
        CreateCustomerResponseDto createdCustomer = customerMapper.entityToDto(
                customerRepo.save(customerMapper.dtoToEntity(requestDto, createdUser.getUserid())));
        log.info("Customer created successfully. Customer: {} ", createdCustomer);
        return createdCustomer;
    }
}
