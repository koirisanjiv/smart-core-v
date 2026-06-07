package com.qaverse.smart.core.validation;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Validator;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public final class TitleValidator
        implements Validator {

    @Override
    public ValidationResult validate(
            ActionRequest request,
            ExecutionContext context) {

        String expected =
                String.valueOf(
                        request.getValue()
                );

        String actual =
                context.getDriver()
                       .getTitle();

        return expected.equals(actual)
                ? ValidationResult.success(
                        ValidatorType.TITLE_MATCH,
                        ValidationMessages.VALIDATION_SUCCESS
                )
                : ValidationResult.failure(
                        ValidatorType.TITLE_MATCH,
                        "Expected title: "
                                + expected
                                + ", Actual: "
                                + actual
                );
    }
    
    @Override
    public ActionType getSupportedAction() {
        return ActionType.TEXT_READ;
    }
}