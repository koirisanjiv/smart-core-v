package com.qaverse.smart.core.exception;

public class ExecutionException
        extends SmartCoreException {

    private static final long serialVersionUID = 1L;

    public ExecutionException(
            String message) {

        super(message);
    }

    public ExecutionException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}