package com.qaverse.smart.core.recovery;

public final class VisibilityRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.SCROLL_TO_ELEMENT;
    }

    @Override
    public RecoveryDecision recover(
            RecoveryContext context) {

        try {

            return new RecoveryDecision(
                    RecoveryDecisionType.RECOVERED,
                    "Element visibility recovered"
            );

        } catch (Exception ex) {

            return new RecoveryDecision(
                    RecoveryDecisionType.NOT_RECOVERED,
                    ex.getMessage()
            );
        }
    }
}