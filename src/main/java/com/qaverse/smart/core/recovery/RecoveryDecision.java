package com.qaverse.smart.core.recovery;

public final class RecoveryDecision {

    private final RecoveryDecisionType decisionType;

    private final String message;

    public RecoveryDecision(
            RecoveryDecisionType decisionType,
            String message) {

        this.decisionType = decisionType;
        this.message = message;
    }

    public RecoveryDecisionType getDecisionType() {
        return decisionType;
    }

    public String getMessage() {
        return message;
    }
}