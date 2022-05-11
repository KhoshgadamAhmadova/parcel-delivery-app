package com.guava.customerms.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDto {

    @NotBlank(message = "UserName can not be empty")
    private String username;

    @NotBlank(message = "Password can not be empty")
    private String password;

    @NotBlank(message = "First name can not be empty")
    private String firstName;

    private String lastName;

    @Email(regexp = ".+[@].+[\\.].+", message = "Enter valid email address")
    private String email;

    @Digits(integer = 9, fraction = 0, message = "Number should consist of 9 numbers")
    private String phoneNumber;

}
