package com.qaverse.smart.core.recovery;

import java.util.Objects;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.failure.FailureContext;

public final class RecoveryContext {

    private final FailureContext failureContext;

    private final ExecutionContext executionContext;

    public RecoveryContext(
            FailureContext failureContext,
            ExecutionContext executionContext) {

    	this.failureContext =
    	        Objects.requireNonNull(
    	                failureContext,
    	                RecoveryMessages.FAILURE_CONTEXT_NULL
    	        );

    	this.executionContext =
    	        Objects.requireNonNull(
    	                executionContext,
    	                RecoveryMessages.EXECUTION_CONTEXT_NULL
    	        );
    }

    public FailureContext getFailureContext() {
        return failureContext;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
}