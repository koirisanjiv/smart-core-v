package com.qaverse.smart.core.engine;

import com.qaverse.smart.core.bootstrap.SmartCoreBootstrap;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;
import com.qaverse.smart.core.recovery.RecoveryEngine;

public final class SmartExecutionEngine
        implements ExecutionEngine {

    private final ObservationEngine observationEngine;

    private final ValidationEngine validationEngine;

    private final RecoveryEngine recoveryEngine;

    private final RetryEngine retryEngine;

    public SmartExecutionEngine() {

        SmartCoreBootstrap.initialize();

        this.observationEngine =
                new ObservationEngine();

        this.validationEngine =
                new ValidationEngine();

        this.recoveryEngine =
                new RecoveryEngine();

        this.retryEngine =
                new RetryEngine();
    }

    @Override
    public ActionResult execute(
            ActionRequest request,
            ExecutionContext context) {

        return retryEngine.execute(
                request,
                context,
                observationEngine,
                validationEngine,
                recoveryEngine
        );
    }
}