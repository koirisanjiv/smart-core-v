package com.qaverse.smart.core.recovery;

import java.util.List;

import com.qaverse.smart.core.registry.RecoveryRegistry;

public final class RecoveryEngine {

    public RecoveryDecision recover(
            RecoveryContext context) {

        List<BaseRecoveryStrategy> strategies =
                RecoveryRegistry.getAll();

        for (BaseRecoveryStrategy strategy :
                strategies) {

            RecoveryDecision decision =
                    strategy.recover(context);

            if (decision.getDecisionType()
                    == RecoveryDecisionType.RECOVERED) {

                return decision;
            }
        }

        return new RecoveryDecision(
                RecoveryDecisionType.NOT_RECOVERED,
                "Recovery failed"
        );
    }
}