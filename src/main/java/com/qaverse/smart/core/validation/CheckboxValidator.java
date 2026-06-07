package com.qaverse.smart.core.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class CheckboxValidator
        implements Validator {

    @Override
    public ValidationResult validate(
            ActionRequest request,
            ExecutionContext context) {

        if (!(request.getTarget() instanceof By)) {

            return ValidationResult.VALID;
        }

        WebElement element =
                context.getDriver()
                        .findElement(
                                (By) request.getTarget()
                        );

        boolean expected =
                Boolean.parseBoolean(
                        String.valueOf(
                                request.getValue()
                        )
                );

        boolean actual =
                element.isSelected();

        if (expected != actual) {

            return ValidationResult.failure(
                    ValidatorType.ELEMENT_SELECTED,
                    ValidationMessages.CHECKBOX_STATE_MISMATCH
            );
        }

        return ValidationResult.success(
                ValidatorType.ELEMENT_SELECTED,
                ValidationMessages.VALIDATION_SUCCESS
        );
    }
    
    @Override
    public ActionType getSupportedAction() {
        return ActionType.CHECKBOX;
    }
}