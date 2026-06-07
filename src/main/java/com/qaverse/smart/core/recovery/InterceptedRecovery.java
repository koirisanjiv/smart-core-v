package com.qaverse.smart.core.recovery;

public final class InterceptedRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.SCROLL_TO_ELEMENT;
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
                    "Click interception recovered"
            );

        } catch (Exception ex) {

            return new RecoveryDecision(
                    RecoveryDecisionType.NOT_RECOVERED,
                    ex.getMessage()
            );
        }
    }
}