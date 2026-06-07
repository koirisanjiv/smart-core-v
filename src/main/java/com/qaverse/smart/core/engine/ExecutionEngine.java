package com.qaverse.smart.core.engine;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public interface ExecutionEngine {

    ActionResult execute(
            ActionRequest request,
            ExecutionContext context
    );
}