package com.guava.deliveryms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDetailsDto {

    @NotBlank(message = "city {javax.validation.constraints.NotBlank.message}")
    private String city;

    @NotBlank(message = "address {javax.validation.constraints.NotBlank.message}")
    private String address;

    private String coordinates;

    @NotBlank(message = "contactPhone {javax.validation.constraints.NotBlank.message}")
    private String contactPhone;
}
