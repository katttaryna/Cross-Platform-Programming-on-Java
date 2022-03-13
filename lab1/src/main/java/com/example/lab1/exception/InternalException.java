package com.example.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed...")
public class InternalException extends RuntimeException {
    public InternalException (String message) {
        super(message);
    }
}
