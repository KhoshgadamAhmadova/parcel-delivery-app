package com.guava.deliveryms.constant;

import org.springframework.stereotype.Component;

@Component("Role")
public final class UserRole {
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String COURIER = "ROLE_COURIER";
}
