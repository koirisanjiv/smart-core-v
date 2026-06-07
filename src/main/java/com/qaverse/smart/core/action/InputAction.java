package com.qaverse.smart.core.action;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public class InputAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.INPUT;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {

    	return ActionResult.success(
    	        ActionType.INPUT,
    	        "Input successful",
    	        0L
    	);
    }
}