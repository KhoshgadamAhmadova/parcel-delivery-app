package com.guava.courierms.service;

import com.guava.courierms.client.AuthMsFeignClient;
import com.guava.courierms.constant.UserStatus;
import com.guava.courierms.dto.CourierResponseDto;
import com.guava.courierms.dto.CourierStatusDto;
import com.guava.courierms.dto.CreateCourierRequestDto;
import com.guava.courierms.dto.feign.UserResponseDto;
import com.guava.courierms.entity.CourierEntity;
import com.guava.courierms.exception.DataNotFoundException;
import com.guava.courierms.mapper.CourierMapper;
import com.guava.courierms.mapper.UserMapper;
import com.guava.courierms.repository.CourierRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourierService {

    private static final String COURIER_ROLE = "COURIER";

    private final CourierRepository courierRepo;
    private final CourierMapper courierMapper;
    private final UserMapper userMapper;
    private final AuthMsFeignClient authMsFeignClient;

    public CourierResponseDto createCourierAccount(CreateCourierRequestDto courierRequestDto) {
        UserResponseDto createdUser = authMsFeignClient.createUser(
                userMapper.toUserRequestDto(courierRequestDto, COURIER_ROLE, UserStatus.ACTIVE));
        CourierEntity createdCourier = courierRepo.save(courierMapper.dtoToEntity(courierRequestDto,
                createdUser.getUserid()));
        log.info("Courier account created successfully. Courier: {} ", createdCourier);
        return courierMapper.entityToDto(createdCourier);
    }


    public List<CourierStatusDto> getCourierStatus() {
        return courierMapper.entityToDto(courierRepo.findAll());
    }

    public void updateCourierStatus(CourierStatusDto reqDto) {
        String courierUserid = reqDto.getCourierUserid();
        CourierEntity courierEntity = courierRepo.getCourierEntityByCourierUserid(courierUserid)
                .orElseThrow(
                        () -> new DataNotFoundException(String.format("Courier %s doesn't exist.", courierUserid)));
        courierMapper.dtoToEntity(reqDto, courierEntity);
        CourierEntity updatedCourier = courierRepo.save(courierEntity);
        log.info("Courier status updated successfully. Courier: {}", updatedCourier);
    }


}
