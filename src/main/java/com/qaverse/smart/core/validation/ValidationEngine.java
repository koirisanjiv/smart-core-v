package com.qaverse.smart.core.validation;

import java.util.List;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.event.EventPublisher;
import com.qaverse.smart.core.event.ValidationFailedEvent;
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

            ValidationResult result =
                    validator.validate(
                            request,
                            context
                    );

            if (!result.isValid()) {

                EventPublisher.publish(
                        ValidationFailedEvent.builder()
                                .validatorType(
                                        result.getValidatorType()
                                )
                                .message(
                                        result.getMessage()
                                )
                                .build()
                );

                throw new ValidationException(
                        result.getMessage()
                );
            }
        }
    }
}