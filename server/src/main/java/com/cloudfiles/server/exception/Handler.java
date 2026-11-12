package com.cloudfiles.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.BiFunction;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(EmailRegisteredException.class)
    ResponseEntity<?> handle(EmailRegisteredException e) {
        return response.apply(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<?> handle(UserNotFoundException e) {
        return response.apply(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<?> handle(AccessDeniedException e) {
        return response.apply(HttpStatus.FORBIDDEN, e);
    }

    private final BiFunction<HttpStatus, RuntimeException, ResponseEntity<?>> response =
            (status, e) -> new ResponseEntity<>(e.getMessage(), status);
}
