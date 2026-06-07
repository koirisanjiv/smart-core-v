package com.qaverse.smart.core.action;

import com.qaverse.smart.core.contract.Action;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public class ClickAction
        implements Action {

    @Override
    public ActionType getType() {
        return ActionType.CLICK;
    }

    @Override
    public ActionResult execute(
            ActionRequest request) {

        // click logic

    	return ActionResult.success(
    	        ActionType.CLICK,
    	        "Click successful",
    	        0L
    	);
    }
}