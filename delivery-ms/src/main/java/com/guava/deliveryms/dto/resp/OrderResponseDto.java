package com.guava.deliveryms.dto.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {

    private String orderId;

    private String orderStatus;

    private String courierId;
}
