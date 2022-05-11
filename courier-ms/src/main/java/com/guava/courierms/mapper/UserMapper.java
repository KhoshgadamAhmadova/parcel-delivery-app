package com.guava.courierms.mapper;


import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.courierms.constant.UserStatus;
import com.guava.courierms.dto.CreateCourierRequestDto;
import com.guava.courierms.dto.feign.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface UserMapper {

    UserRequestDto toUserRequestDto(CreateCourierRequestDto requestDto, String role, UserStatus status);

}
