package com.guava.deliveryms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    NEW(UserRole.CUSTOMER),
    CANCELLED(UserRole.CUSTOMER),
    IN_PICK_UP(UserRole.ADMIN),
    IN_TRANSIT(UserRole.COURIER),
    DELIVERED(UserRole.COURIER);

    private final String responsibleProfile;
}
