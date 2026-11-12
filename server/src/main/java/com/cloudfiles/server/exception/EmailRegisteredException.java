package com.cloudfiles.server.exception;

public class EmailRegisteredException extends RuntimeException {
    public EmailRegisteredException(String email) {
        super(email + " is already registered");
    }
}
