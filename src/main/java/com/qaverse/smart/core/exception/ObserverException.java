package com.qaverse.smart.core.exception;

public class ObserverException
        extends SmartCoreException {

    private static final long serialVersionUID = 1L;

    public ObserverException(
            String message) {

        super(message);
    }

    public ObserverException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}