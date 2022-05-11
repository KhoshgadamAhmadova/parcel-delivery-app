package com.guava.deliveryms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DatNotAccessibleException extends RuntimeException {
    public DatNotAccessibleException(String message) {
        super(message);
    }
}
