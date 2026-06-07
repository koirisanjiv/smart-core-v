package com.qaverse.smart.core.recovery;

import java.util.List;

import com.qaverse.smart.core.registry.RecoveryRegistry;

public final class RecoveryEngine {

	public RecoveryDecision recover(
	        RecoveryContext context) {

	    RecoveryType requiredRecovery =
	            RecoveryMapping.get(
	                    context.getFailureContext()
	                           .getFailureType()
	            );

	    for (BaseRecoveryStrategy strategy :
	            RecoveryRegistry.getAll()) {

	        if (strategy.getType()
	                != requiredRecovery) {

	            continue;
	        }

	        return strategy.recover(
	                context
	        );
	    }

	    return new RecoveryDecision(
	            RecoveryDecisionType.NOT_RECOVERED,
	            RecoveryMessages.NO_RECOVERY_STRATEGY_FOUND
	    );
	}
}