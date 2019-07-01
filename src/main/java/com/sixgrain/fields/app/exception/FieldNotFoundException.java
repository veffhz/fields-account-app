package com.sixgrain.fields.app.exception;

public class FieldNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Field does not exist";

    public FieldNotFoundException() {
        super(MESSAGE);
    }

    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
