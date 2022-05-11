package com.guava.deliveryms.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.guava.deliveryms.dto.AddressDetailsDto;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequestDto {


    @Valid
    @NotNull(message = "pickupDetails must not be null")
    private AddressDetailsDto pickupDetails;

    @Valid
    @NotNull(message = "deliveryDetails must not be null")
    private AddressDetailsDto deliveryDetails;

}
