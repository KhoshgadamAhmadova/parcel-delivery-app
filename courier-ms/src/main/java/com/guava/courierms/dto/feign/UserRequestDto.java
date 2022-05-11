package com.guava.courierms.dto.feign;

import com.guava.courierms.constant.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    private String username;

    private String password;

    private String role;

    private UserStatus status;

}
