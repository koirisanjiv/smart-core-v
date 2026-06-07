package com.qaverse.smart.core.registry;

public class RegistryException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RegistryException(
            String message) {

        super(message);
    }

    public RegistryException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}