package com.guava.customerms.mapper;


import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.customerms.constant.UserStatus;
import com.guava.customerms.dto.CreateCustomerRequestDto;
import com.guava.customerms.dto.feign.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface UserMapper {

    UserRequestDto toUserRequestDto(CreateCustomerRequestDto requestDto, String role, UserStatus status);

}
