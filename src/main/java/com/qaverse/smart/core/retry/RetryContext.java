package com.qaverse.smart.core.retry;

import com.qaverse.smart.core.failure.FailureContext;

public final class RetryContext {

    private final FailureContext failureContext;

    private final int currentAttempt;

    public RetryContext(
            FailureContext failureContext,
            int currentAttempt) {

        this.failureContext = failureContext;
        this.currentAttempt = currentAttempt;
    }

    public FailureContext getFailureContext() {
        return failureContext;
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }
}