package com.guava.customerms.controller;

import com.guava.customerms.dto.CreateCustomerRequestDto;
import com.guava.customerms.dto.CreateCustomerResponseDto;
import com.guava.customerms.service.CustomerManagementService;
import io.swagger.v3.oas.annotations.Operation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Validated
public class CustomerManagementController {

    private final CustomerManagementService customerManagementService;

    @Operation(summary = "Create new customer")
    @PostMapping
    public ResponseEntity<CreateCustomerResponseDto> createCustomer(
            @RequestBody @Valid CreateCustomerRequestDto requestDto) {
        return ResponseEntity.ok(customerManagementService.createCustomer(requestDto));
    }

}
