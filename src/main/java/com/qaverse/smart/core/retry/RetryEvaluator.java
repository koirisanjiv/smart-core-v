package com.qaverse.smart.core.retry;

public final class RetryEvaluator {

    public RetryDecision evaluate(
            RetryContext context) {

        RetryPolicy policy =
                RetryPolicyRegistry.get(
                        context.getFailureContext()
                               .getFailureType()
                );

        if (context.getCurrentAttempt() >=
                policy.getMaxAttempts()) {

            return new RetryDecision(
                    RetryDecisionType.FAIL_FAST,
                    "Max retry attempts reached"
            );
        }

        if (policy.isRecoveryAllowed()) {

            return new RetryDecision(
                    RetryDecisionType.RECOVER_THEN_RETRY,
                    "Recovery allowed"
            );
        }

        return new RetryDecision(
                RetryDecisionType.RETRY,
                "Retry allowed"
        );
    }
}