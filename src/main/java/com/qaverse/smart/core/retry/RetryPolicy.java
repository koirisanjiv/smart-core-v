package com.qaverse.smart.core.retry;

import com.qaverse.smart.core.failure.FailureType;

public final class RetryPolicy {

    private final FailureType failureType;

    private final int maxAttempts;

    private final boolean recoveryAllowed;

    public RetryPolicy(
            FailureType failureType,
            int maxAttempts,
            boolean recoveryAllowed) {

        this.failureType = failureType;
        this.maxAttempts = maxAttempts;
        this.recoveryAllowed = recoveryAllowed;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean isRecoveryAllowed() {
        return recoveryAllowed;
    }
}