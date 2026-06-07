package com.qaverse.smart.core.failure;

public final class FailureClassificationResult {

    private final FailureType failureType;

    private final FailureCategory category;

    private final FailureDisposition disposition;

    public FailureClassificationResult(
            FailureType failureType,
            FailureCategory category,
            FailureDisposition disposition) {

        this.failureType = failureType;
        this.category = category;
        this.disposition = disposition;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public FailureCategory getCategory() {
        return category;
    }

    public FailureDisposition getDisposition() {
        return disposition;
    }
}