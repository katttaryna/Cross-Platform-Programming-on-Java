package com.example.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong value...")
public class Exception extends RuntimeException {

    public Exception(String message) {
        super(message);
    }
}





