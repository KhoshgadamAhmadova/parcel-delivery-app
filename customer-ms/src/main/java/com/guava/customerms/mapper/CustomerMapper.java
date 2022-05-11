package com.guava.customerms.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.customerms.dto.CreateCustomerRequestDto;
import com.guava.customerms.dto.CreateCustomerResponseDto;
import com.guava.customerms.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerEntity dtoToEntity(CreateCustomerRequestDto requestDto, String userId);

    CreateCustomerResponseDto entityToDto(CustomerEntity entity);

}
