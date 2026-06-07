package com.qaverse.smart.core.recovery;

public interface BaseRecoveryStrategy {

    RecoveryType getType();

    RecoveryDecision recover(
            RecoveryContext context);
}