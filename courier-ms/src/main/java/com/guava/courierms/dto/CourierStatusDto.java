package com.guava.courierms.dto;

import com.guava.courierms.constant.CourierStatus;
import lombok.Data;

@Data
public class CourierStatusDto {

    private String courierUserid;

    private CourierStatus status;

}
