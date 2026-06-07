package com.qaverse.smart.core.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class InputValidator
        implements Validator {

    @Override
    public ValidationResult validate(
            ActionRequest request,
            ExecutionContext context) {

        if (!(request.getTarget() instanceof By)) {

            return ValidationResult.VALID;
        }

        if (request.getValue() == null) {

            return ValidationResult.VALID;
        }

        WebElement element =
                context.getDriver()
                        .findElement(
                                (By) request.getTarget()
                        );

        String actual =
                element.getAttribute(
                        "value"
                );

        String expected =
                String.valueOf(
                        request.getValue()
                );

        if (!expected.equals(actual)) {

            return ValidationResult.failure(
                    ValidatorType.TEXT_MATCH,
                    ValidationMessages.INPUT_VALUE_MISMATCH
            );
        }

        return ValidationResult.success(
                ValidatorType.TEXT_MATCH,
                ValidationMessages.VALIDATION_SUCCESS
        );
    }
    
    @Override
    public ActionType getSupportedAction() {
        return ActionType.INPUT;
    }
}