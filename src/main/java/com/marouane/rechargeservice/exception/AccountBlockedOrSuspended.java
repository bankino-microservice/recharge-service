package com.marouane.rechargeservice.exception;

public class AccountBlockedOrSuspended extends RuntimeException {
    public AccountBlockedOrSuspended(String message) {
        super(message);
    }
}
