package com.sixgrain.fields.app.exception;

public class AccountNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Account does not exist";

    public AccountNotFoundException() {
        super(MESSAGE);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
