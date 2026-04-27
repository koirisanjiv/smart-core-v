package com.qaverse.smart.Exception;

import com.qaverse.smart.Failure.FailureType;

@SuppressWarnings("serial")
public class ExceptionManager extends RuntimeException {

    private final FailureType type;

    public ExceptionManager(FailureType type, String message) {
        super(message);
        this.type = type;
    }

    public ExceptionManager(FailureType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public FailureType getType() {
        return type;
    }
}
