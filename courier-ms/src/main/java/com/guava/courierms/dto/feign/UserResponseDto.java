package com.guava.courierms.dto.feign;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private String userid;

    private String username;

    private String role;
}
