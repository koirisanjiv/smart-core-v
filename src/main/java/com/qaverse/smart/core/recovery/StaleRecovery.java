package com.qaverse.smart.core.recovery;

public class StaleRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.RELOCATE_ELEMENT;
    }

    @Override
    public RecoveryDecision recover(
            RecoveryContext context) {

        return new RecoveryDecision(
                RecoveryDecisionType.RECOVERED,
                "Stale element recovered"
        );
    }
}