package com.qaverse.smart.core.diagnostic;

public class DiagnosticException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DiagnosticException(
            String message) {

        super(message);
    }

    public DiagnosticException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}