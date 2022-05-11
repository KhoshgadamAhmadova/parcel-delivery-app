package com.guava.customerms.client;


import com.guava.customerms.dto.feign.UserRequestDto;
import com.guava.customerms.dto.feign.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "auth-ms", url = "${feignclient.auth-ms}")
public interface AuthMsFeignClient {


    @PostMapping
    UserResponseDto createUser(@RequestBody UserRequestDto request);


}
