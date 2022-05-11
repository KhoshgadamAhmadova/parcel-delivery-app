package com.guava.deliveryms.dto.resp;

import com.guava.deliveryms.dto.AddressDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsResponseDto {

    private String orderId;

    private String orderStatus;

    private String courierId;

    private AddressDetailsDto pickupDetails;

    private AddressDetailsDto deliveryDetails;
}
