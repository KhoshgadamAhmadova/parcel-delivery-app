package com.guava.courierms.controller;

import com.guava.courierms.dto.CourierStatusDto;
import com.guava.courierms.dto.CreateCourierRequestDto;
import com.guava.courierms.service.CourierService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PreAuthorize("hasAnyRole(@Role.ADMIN)")
    @PostMapping
    public ResponseEntity<Object> createCourier(
            @RequestBody @Valid CreateCourierRequestDto requestDto) {
        return ResponseEntity.ok(courierService.createCourierAccount(requestDto));
    }

    @PutMapping("/status")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCourierStatus(@RequestBody @Valid CourierStatusDto requestDto) {
        courierService.updateCourierStatus(requestDto);
    }

    @PreAuthorize("hasAnyRole(@Role.ADMIN)")
    @GetMapping("/status")
    public ResponseEntity<Object> geCourierStatus() {
        return ResponseEntity.ok(courierService.getCourierStatus());
    }
}
