package com.qaverse.smart.core.model;

public final class RetryResult {

    private final boolean retryRequired;
    private final int currentAttempt;
    private final int maxAttempts;
    private final long delayMs;

    public RetryResult(
            boolean retryRequired,
            int currentAttempt,
            int maxAttempts,
            long delayMs) {

        this.retryRequired = retryRequired;
        this.currentAttempt = currentAttempt;
        this.maxAttempts = maxAttempts;
        this.delayMs = delayMs;
    }

    public boolean isRetryRequired() {
        return retryRequired;
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long getDelayMs() {
        return delayMs;
    }
}