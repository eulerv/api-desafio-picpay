package com.github.eulerv.picpaydesafiobackend.authorization;

public class TransactionExceptionError extends RuntimeException {
    public TransactionExceptionError(String message) {
        super(message);
    }    
}
