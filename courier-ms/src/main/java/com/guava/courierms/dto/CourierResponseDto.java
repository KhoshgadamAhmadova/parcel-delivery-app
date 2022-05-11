package com.guava.courierms.dto;

import com.guava.courierms.constant.CourierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourierResponseDto {

    private String courierId;

    private String courierUserid;

    private String firstName;

    private String lastName;

    private CourierType courierType;

    private String status;

}
