package com.qaverse.smart.core.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class ClickValidator
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

        if (!element.isDisplayed()
                || !element.isEnabled()) {

            return ValidationResult.failure(
                    ValidatorType.ELEMENT_CLICKABLE,
                    ValidationMessages.ELEMENT_NOT_CLICKABLE
            );
        }

        return ValidationResult.success(
                ValidatorType.ELEMENT_CLICKABLE,
                ValidationMessages.VALIDATION_SUCCESS
        );
    }
    
    @Override
    public ActionType getSupportedAction() {
        return ActionType.CLICK;
    }
}