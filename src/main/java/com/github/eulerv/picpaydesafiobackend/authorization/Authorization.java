package com.github.eulerv.picpaydesafiobackend.authorization;

public record Authorization(
    String status,
    Object data
) {
    public boolean isAuthorized() {
        return status.equals("success");
    }
}