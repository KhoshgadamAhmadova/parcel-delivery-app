package com.guava.authms.controller;

import com.guava.authms.dto.UserRequestDto;
import com.guava.authms.service.DeliveryAppUserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class DeliveryUserController {

    private final DeliveryAppUserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.addUser(requestDto));
    }

}
