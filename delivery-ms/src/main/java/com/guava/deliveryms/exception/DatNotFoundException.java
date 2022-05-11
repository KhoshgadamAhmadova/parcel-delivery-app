package com.guava.deliveryms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DatNotFoundException extends RuntimeException {
    public DatNotFoundException(String message) {
        super(message);
    }
}
