package com.qaverse.smart.core.failure;

public final class FailureContext {

    private final Throwable throwable;

    private final FailureType failureType;

    private final FailureCategory category;

    private final FailureSeverity severity;

    private final String message;

    public FailureContext(
            Throwable throwable,
            FailureType failureType,
            FailureCategory category,
            FailureSeverity severity,
            String message) {

        this.throwable = throwable;
        this.failureType = failureType;
        this.category = category;
        this.severity = severity;
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public FailureCategory getCategory() {
        return category;
    }

    public FailureSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}