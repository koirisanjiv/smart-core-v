package com.qaverse.smart.core.retry;

import com.qaverse.smart.core.failure.FailureAnalyzer;
import com.qaverse.smart.core.failure.FailureDisposition;

public final class RetryEvaluator {

    public RetryDecision evaluate(
            RetryContext context) {

        RetryPolicy policy =
                RetryPolicyRegistry.get(
                        context.getFailureContext()
                               .getFailureType()
                );

        FailureDisposition disposition =
                FailureAnalyzer.analyze(
                        context.getFailureContext()
                               .getFailureType()
                ).getDisposition();

        if (disposition
                == FailureDisposition.FAIL_FAST) {

            return new RetryDecision(
                    RetryDecisionType.FAIL_FAST,
                    RetryReason.MAX_ATTEMPTS_REACHED
            );
        }

        if (context.getCurrentAttempt()
                >= policy.getMaxAttempts()) {

            return new RetryDecision(
                    RetryDecisionType.FAIL_FAST,
                    RetryReason.MAX_ATTEMPTS_REACHED
            );
        }

        if (disposition
                == FailureDisposition.RECOVER
                && policy.isRecoveryAllowed()) {

            return new RetryDecision(
                    RetryDecisionType.RECOVER_THEN_RETRY,
                    RetryReason.RECOVERY_ALLOWED
            );
        }

        return new RetryDecision(
                RetryDecisionType.RETRY,
                RetryReason.RETRY_ALLOWED
        );
    }
}