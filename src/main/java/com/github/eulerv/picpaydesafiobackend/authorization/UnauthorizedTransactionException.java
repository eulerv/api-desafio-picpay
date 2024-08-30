package com.github.eulerv.picpaydesafiobackend.authorization;

public class UnauthorizedTransactionException extends RuntimeException{
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
