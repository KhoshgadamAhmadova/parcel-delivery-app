package com.guava.courierms.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.courierms.dto.CourierResponseDto;
import com.guava.courierms.dto.CourierStatusDto;
import com.guava.courierms.dto.CreateCourierRequestDto;
import com.guava.courierms.entity.CourierEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourierMapper {

    CourierEntity dtoToEntity(CreateCourierRequestDto requestDto, String courierUserid);

    CourierResponseDto entityToDto(CourierEntity entity);

    List<CourierStatusDto> entityToDto(List<CourierEntity> entity);


    void dtoToEntity(CourierStatusDto orderRequestDto, @MappingTarget CourierEntity entity);


}
