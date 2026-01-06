package com.marouane.rechargeservice.exception;

public class AccountFeignException extends RuntimeException {

    public AccountFeignException(String message) {
        super(message);
    }

    public AccountFeignException(String message, Throwable cause) {
        super(message, cause);
    }
}
