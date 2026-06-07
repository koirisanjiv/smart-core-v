package com.qaverse.smart.core.failure;
public final class FailureDecision {

    private final FailureDisposition disposition;

    private final FailureReason reason;

    public FailureDecision(
            FailureDisposition disposition,
            FailureReason reason) {

        this.disposition = disposition;
        this.reason = reason;
    }

    public FailureDisposition getDisposition() {
        return disposition;
    }

    public FailureReason getReason() {
        return reason;
    }
}