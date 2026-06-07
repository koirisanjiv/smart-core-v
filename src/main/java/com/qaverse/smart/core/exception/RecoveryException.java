package com.qaverse.smart.core.exception;

public class RecoveryException
        extends SmartCoreException {

    private static final long serialVersionUID = 1L;

    public RecoveryException(
            String message) {

        super(message);
    }

    public RecoveryException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}