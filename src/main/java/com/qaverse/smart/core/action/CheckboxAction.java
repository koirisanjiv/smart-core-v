package com.qaverse.smart.core.action;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.engine.runtime.EngineManager;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class CheckboxAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.CHECKBOX;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {

        return EngineManager
                .getEngine()
                .checkbox(request);
    }
}