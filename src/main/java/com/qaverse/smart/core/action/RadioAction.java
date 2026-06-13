package com.qaverse.smart.core.action;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.engine.runtime.EngineManager;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public final class RadioAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.RADIO;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {

        return EngineManager
                .getEngine()
                .radio(request);
    }
}