package com.qaverse.smart.core.engine;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;
import com.qaverse.smart.core.registry.ActionRegistry;
import com.qaverse.smart.core.recovery.RecoveryEngine;

public final class RetryEngine {

    public ActionResult execute(
            ActionRequest request,
            ExecutionContext context,
            ObservationEngine observationEngine,
            ValidationEngine validationEngine,
            RecoveryEngine recoveryEngine) {

        int attempt = 0;

        Exception lastException = null;

        while (attempt <
                EngineConstants.DEFAULT_MAX_ATTEMPTS) {

            attempt++;

            try {

                observationEngine.observe(
                        context
                );

                ActionResult result =
                        ActionRegistry
                                .getInstance()
                                .get(
                                        request.getActionType()
                                )
                                .execute(
                                        request
                                );

                validationEngine.validate(
                        request,
                        context
                );

                return result;

            } catch (Exception ex) {

                lastException = ex;
            }
        }

        throw new RuntimeException(
                "Execution failed after "
                        + attempt
                        + " attempts",
                lastException
        );
    }
}