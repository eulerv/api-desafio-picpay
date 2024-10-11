package com.github.eulerv.picpaydesafiobackend.exception;

public class UnableToRequestAuthorization extends RuntimeException {
    public UnableToRequestAuthorization(String message) {
        super(message);
    }
    public UnableToRequestAuthorization(String message, Throwable cause) {
        super(message, cause);
    }
}
