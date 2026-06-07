package com.qaverse.smart.core.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class VisibilityValidator
        implements Validator {

    @Override
    public ValidationResult validate(
            ActionRequest request,
            ExecutionContext context) {

        Object target =
                request.getTarget();

        if (!(request.getTarget() instanceof By)) {

            return ValidationResult.VALID;
        }

        WebElement element =
                context.getDriver()
                       .findElement(
                               (By) request.getTarget()
                       );

        return element.isDisplayed()
                ? ValidationResult.success(
                        ValidatorType.ELEMENT_VISIBLE,
                        ValidationMessages.VALIDATION_SUCCESS
                )
                : ValidationResult.failure(
                        ValidatorType.ELEMENT_VISIBLE,
                        ValidationMessages.VALIDATION_FAILED
                );
    }

    @Override
    public ActionType getSupportedAction() {
        return ActionType.CLICK;
    }
}