package com.qaverse.smart.core.recovery;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.failure.FailureContext;

public final class RecoveryContext {

    private final FailureContext failureContext;

    private final ExecutionContext executionContext;

    public RecoveryContext(
            FailureContext failureContext,
            ExecutionContext executionContext) {

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