package com.example.lab1.loggerErrors;

import com.example.lab1.exception.Exception;
import com.example.lab1.exception.InternalException;
import com.example.lab1.response.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class LoggerErrors extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(@NotNull Exception e) {
        logger.error("ERROR CODE 400", e);
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Response> handleException(@NotNull InternalException e) {
        logger.error("ERROR CODE 500", e);
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

