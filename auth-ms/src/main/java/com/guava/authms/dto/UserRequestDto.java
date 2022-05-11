package com.guava.authms.dto;

import com.guava.authms.constant.RoleType;
import com.guava.authms.constant.UserStatus;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "username {javax.validation.constraints.NotBlank.message}")
    private String username;

    @NotBlank(message = "password {javax.validation.constraints.NotBlank.message}")
    private String password;

    @NotNull(message = "role {javax.validation.constraints.NotNull.message}")
    private RoleType role;

    @NotNull(message = "status {javax.validation.constraints.NotNull.message}")
    private UserStatus status;

}
