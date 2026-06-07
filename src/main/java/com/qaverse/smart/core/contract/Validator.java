package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ValidationResult;

public interface Validator {

    ValidationResult validate(
            ActionRequest request,
            ExecutionContext context
    );

}