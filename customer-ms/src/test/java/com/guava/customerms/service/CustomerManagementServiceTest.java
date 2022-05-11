package com.guava.customerms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.guava.customerms.client.AuthMsFeignClient;
import com.guava.customerms.constant.UserStatus;
import com.guava.customerms.dto.CreateCustomerResponseDto;
import com.guava.customerms.mapper.CustomerMapper;
import com.guava.customerms.mapper.UserMapper;
import com.guava.customerms.mockdata.CustomerMockData;
import com.guava.customerms.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerManagementServiceTest {

    private CustomerManagementService service;
    private CustomerMockData mockData;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthMsFeignClient authMsFeignClient;

    @BeforeEach
    void setUp() {
        service = new CustomerManagementService(customerRepository, customerMapper,
                authMsFeignClient, userMapper);
        mockData = new CustomerMockData();

    }

    @Test
    @Disabled
    void itShouldCreateCustomer() {
        //given
        var customerEntity = mockData.getCustomerEntity();
        var requestDto = mockData.getCreateCustomerRequestDto();
        var responseDto = mockData.getCreateCustomerResponseDto();

        when(userMapper.toUserRequestDto(any(), anyString(), any(UserStatus.class)))
                .thenReturn(mockData.getUserRequestDto());
        when(authMsFeignClient.createUser(any())).thenReturn(mockData.getUserResponseDto());


        when(customerMapper.dtoToEntity(any(), anyString())).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.entityToDto(customerEntity)).thenReturn(responseDto);

        //when
        CreateCustomerResponseDto serviceResponse = service.createCustomer(requestDto);

        //then
        assertThat(serviceResponse).isEqualTo(responseDto);
        verify(customerMapper, times(1)).dtoToEntity(
                requestDto, CustomerMockData.USER_ID);
        verify(customerMapper, times(1)).entityToDto(customerEntity);
        verify(authMsFeignClient, times(1)).createUser(mockData.getUserRequestDto());
        verify(userMapper, times(1)).toUserRequestDto(mockData.getCreateCustomerRequestDto(), "CUSTOMER",
                UserStatus.ACTIVE);
        verify(customerRepository, times(1)).save(customerEntity);

    }
}