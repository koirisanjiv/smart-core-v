package com.qaverse.smart.core.retry;

public final class RetryDecision {

    private final RetryDecisionType decisionType;

    private final String reason;

    public RetryDecision(
            RetryDecisionType decisionType,
            String reason) {

        this.decisionType = decisionType;
        this.reason = reason;
    }

    public RetryDecisionType getDecisionType() {
        return decisionType;
    }

    public String getReason() {
        return reason;
    }
}