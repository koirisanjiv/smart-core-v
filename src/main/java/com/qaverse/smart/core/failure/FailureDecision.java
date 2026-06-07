package com.qaverse.smart.core.failure;

public final class FailureDecision {

    private final FailureDisposition disposition;

    private final String reason;

    public FailureDecision(
            FailureDisposition disposition,
            String reason) {

        this.disposition = disposition;
        this.reason = reason;
    }

    public FailureDisposition getDisposition() {
        return disposition;
    }

    public String getReason() {
        return reason;
    }
}