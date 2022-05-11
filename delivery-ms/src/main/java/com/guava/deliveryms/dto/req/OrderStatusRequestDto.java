package com.guava.deliveryms.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.guava.deliveryms.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusRequestDto {

    private OrderStatus orderStatus;
}
