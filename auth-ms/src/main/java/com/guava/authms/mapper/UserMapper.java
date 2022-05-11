package com.guava.authms.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.authms.dto.UserRequestDto;
import com.guava.authms.dto.UserResponseDto;
import com.guava.authms.entity.UserEntity;
import com.guava.authms.security.DeliveryAppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE,
        uses = MapperHelper.class)
public interface UserMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    UserEntity dtoToEntity(UserRequestDto requestDto);

    UserResponseDto entityToDto(UserEntity role);

    @Mapping(target = "roles", source = "role", qualifiedByName = "mapRoleToGrantedAuthority")
    DeliveryAppUser appUserToUserDetails(UserEntity userEntity);

}
