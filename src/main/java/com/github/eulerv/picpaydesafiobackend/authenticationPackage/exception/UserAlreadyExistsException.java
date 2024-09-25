package com.github.eulerv.picpaydesafiobackend.authenticationPackage.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
