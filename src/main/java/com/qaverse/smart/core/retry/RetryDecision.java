package com.qaverse.smart.core.retry;

public final class RetryDecision {

    private final RetryDecisionType decisionType;

    private final RetryReason reason;

    public RetryDecision(
            RetryDecisionType decisionType,
            RetryReason reason) {

        this.decisionType = decisionType;
        this.reason = reason;
    }

    public RetryDecisionType getDecisionType() {
        return decisionType;
    }

    public RetryReason getReason() {
        return reason;
    }
}