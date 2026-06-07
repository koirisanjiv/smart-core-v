package com.qaverse.smart.core.engine;

import java.util.List;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;
import com.qaverse.smart.core.registry.ValidatorRegistry;

public class ValidationEngine {

    public void validate(
            ActionRequest request,
            ExecutionContext context) {

        List<Validator> validators =
                ValidatorRegistry.getAll();

        for (Validator validator : validators) {

            ValidationResult result =
                    validator.validate(
                            request,
                            context
                    );

            if (!result.isValid()) {

                throw new RuntimeException(
                        result.getMessage()
                );
            }
        }
    }
}