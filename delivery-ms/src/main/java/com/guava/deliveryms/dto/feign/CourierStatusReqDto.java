package com.guava.deliveryms.dto.feign;

import com.guava.deliveryms.constant.CourierStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierStatusReqDto {

    private String courierUserid;

    private CourierStatus status;

}
