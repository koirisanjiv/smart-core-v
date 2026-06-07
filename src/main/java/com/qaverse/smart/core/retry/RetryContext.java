package com.qaverse.smart.core.retry;

import com.qaverse.smart.core.failure.FailureContext;

public final class RetryContext {

    private final FailureContext failureContext;

    private final int currentAttempt;

    public RetryContext(
            FailureContext failureContext,
            int currentAttempt) {
    	
    	
    	if (failureContext == null) {
    	    throw new IllegalArgumentException(
    	            "FailureContext cannot be null"
    	    );
    	}

    	if (currentAttempt < 0) {
    	    throw new IllegalArgumentException(
    	            "CurrentAttempt cannot be negative"
    	    );
    	}

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