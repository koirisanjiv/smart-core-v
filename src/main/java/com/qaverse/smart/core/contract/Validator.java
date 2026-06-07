package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public interface Validator {

    ActionType getSupportedAction();

    ValidationResult validate(
            ActionRequest request,
            ExecutionContext context
    );
}