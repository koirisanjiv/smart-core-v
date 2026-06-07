package com.qaverse.smart.core.diagnostic;

import java.util.Objects;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.failure.FailureContext;

public final class DiagnosticContext {

    private final FailureContext failureContext;

    private final ExecutionContext executionContext;

    public DiagnosticContext(
            FailureContext failureContext,
            ExecutionContext executionContext) {
    	
    	Objects.requireNonNull(
    	        failureContext,
    	        "FailureContext cannot be null"
    	);

    	Objects.requireNonNull(
    	        executionContext,
    	        "ExecutionContext cannot be null"
    	);

        this.failureContext = failureContext;
        this.executionContext = executionContext;
    }

    public FailureContext getFailureContext() {
        return failureContext;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
}