package com.github.eulerv.picpaydesafiobackend.authenticationPackage.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}