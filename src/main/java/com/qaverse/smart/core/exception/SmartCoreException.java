package com.qaverse.smart.core.exception;

public class SmartCoreException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SmartCoreException(
            String message) {

        super(message);
    }

    public SmartCoreException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}