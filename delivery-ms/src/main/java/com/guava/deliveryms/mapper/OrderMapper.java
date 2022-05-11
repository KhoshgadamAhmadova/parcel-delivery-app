package com.guava.deliveryms.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.ReportingPolicy.IGNORE;

import com.guava.deliveryms.dto.req.OrderRequestDto;
import com.guava.deliveryms.dto.resp.OrderDetailsResponseDto;
import com.guava.deliveryms.dto.resp.OrderResponseDto;
import com.guava.deliveryms.entity.OrderEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        injectionStrategy = CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = IGNORE)
public interface OrderMapper {

    @Mapping(source = "deliveryOrderEntity.pickupCity", target = "pickupDetails.city")
    @Mapping(source = "deliveryOrderEntity.pickupAddress", target = "pickupDetails.address")
    @Mapping(source = "deliveryOrderEntity.pickupCoordinates", target = "pickupDetails.coordinates")
    @Mapping(source = "deliveryOrderEntity.pickupContactPhone", target = "pickupDetails.contactPhone")

    @Mapping(source = "deliveryOrderEntity.deliveryCity", target = "deliveryDetails.city")
    @Mapping(source = "deliveryOrderEntity.deliveryAddress", target = "deliveryDetails.address")
    @Mapping(source = "deliveryOrderEntity.deliveryCoordinates", target = "deliveryDetails.coordinates")
    @Mapping(source = "deliveryOrderEntity.deliveryContactPhone", target = "deliveryDetails.contactPhone")
    OrderDetailsResponseDto entityToOrderDetailsDto(OrderEntity deliveryOrderEntity);

    @Mapping(target = "pickupCity", source = "orderRequestDto.pickupDetails.city")
    @Mapping(target = "pickupAddress", source = "orderRequestDto.pickupDetails.address")
    @Mapping(target = "pickupCoordinates", source = "orderRequestDto.pickupDetails.coordinates")
    @Mapping(target = "pickupContactPhone", source = "orderRequestDto.pickupDetails.contactPhone")

    @Mapping(target = "deliveryCity", source = "orderRequestDto.deliveryDetails.city")
    @Mapping(target = "deliveryAddress", source = "orderRequestDto.deliveryDetails.address")
    @Mapping(target = "deliveryCoordinates", source = "orderRequestDto.deliveryDetails.coordinates")
    @Mapping(target = "deliveryContactPhone", source = "orderRequestDto.deliveryDetails.contactPhone")
    OrderEntity dtoToEntity(OrderRequestDto orderRequestDto, String userId, String orderStatus);


    OrderResponseDto entityToOrderDto(OrderEntity deliveryOrderEntity);

    default List<OrderResponseDto> entityListToOrderDtoList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(this::entityToOrderDto)
                .collect(Collectors.toList());
    }


    @Mapping(target = "pickupCity", source = "orderRequestDto.pickupDetails.city")
    @Mapping(target = "pickupAddress", source = "orderRequestDto.pickupDetails.address")
    @Mapping(target = "pickupCoordinates", source = "orderRequestDto.pickupDetails.coordinates")
    @Mapping(target = "pickupContactPhone", source = "orderRequestDto.pickupDetails.contactPhone")

    @Mapping(target = "deliveryCity", source = "orderRequestDto.deliveryDetails.city")
    @Mapping(target = "deliveryAddress", source = "orderRequestDto.deliveryDetails.address")
    @Mapping(target = "deliveryCoordinates", source = "orderRequestDto.deliveryDetails.coordinates")
    @Mapping(target = "deliveryContactPhone", source = "orderRequestDto.deliveryDetails.contactPhone")
    void dtoToEntity(OrderRequestDto orderRequestDto, @MappingTarget OrderEntity entity);

}