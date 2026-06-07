package com.qaverse.smart.core.recovery;

public final class TimeoutRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.WAIT_FOR_DOM;
    }

    @Override
    public RecoveryDecision recover(
            RecoveryContext context) {

        try {

            Thread.sleep(
                    RecoveryConstants.DEFAULT_WAIT_SECONDS
                    * 1000L
            );

            return new RecoveryDecision(
                    RecoveryDecisionType.RECOVERED,
                    RecoveryMessages.TIMEOUT_RECOVERED
            );

        } catch (Exception ex) {

            return new RecoveryDecision(
                    RecoveryDecisionType.NOT_RECOVERED,
                    ex.getMessage()
            );
        }
    }
}