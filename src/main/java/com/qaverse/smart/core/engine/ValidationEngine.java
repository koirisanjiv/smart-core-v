package com.qaverse.smart.core.engine;

import java.util.List;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.exception.ValidationException;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;
import com.qaverse.smart.core.registry.ValidatorRegistry;

public final class ValidationEngine {

    public void validate(
            ActionRequest request,
            ExecutionContext context) {

        List<Validator> validators =
                ValidatorRegistry.getAll();

        for (Validator validator : validators) {
        	
            if (validator.getSupportedAction()
                    != request.getActionType()) {

                continue;
            }

            ValidationResult result =
                    validator.validate(
                            request,
                            context
                    );

            if (!result.isValid()) {

                throw new ValidationException(
                        result.getMessage()
                );
            }
        }
    }
}