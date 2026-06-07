package com.qaverse.smart.core.validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class DropdownValidator
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

        Select select =
                new Select(element);

        String actual =
                select.getFirstSelectedOption()
                        .getText();

        String expected =
                String.valueOf(
                        request.getValue()
                );

        if (!expected.equals(actual)) {

            return ValidationResult.failure(
                    ValidatorType.TEXT_MATCH,
                    ValidationMessages.DROPDOWN_VALUE_MISMATCH
            );
        }

        return ValidationResult.success(
                ValidatorType.TEXT_MATCH,
                ValidationMessages.VALIDATION_SUCCESS
        );
    }
    
    @Override
    public ActionType getSupportedAction() {
        return ActionType.DROPDOWN;
    }
}