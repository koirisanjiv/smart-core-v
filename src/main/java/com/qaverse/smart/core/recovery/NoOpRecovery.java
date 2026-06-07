package com.qaverse.smart.core.recovery;

public class NoOpRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.NONE;
    }

    @Override
    public RecoveryDecision recover(
            RecoveryContext context) {

        return new RecoveryDecision(
                RecoveryDecisionType.NOT_RECOVERED,
                RecoveryMessages.NO_RECOVERY_POSSIBLE
        );
    }
}