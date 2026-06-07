package com.qaverse.smart.core.exception;

public class ValidationException
        extends SmartCoreException {

    private static final long serialVersionUID = 1L;

    public ValidationException(
            String message) {

        super(message);
    }

    public ValidationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}