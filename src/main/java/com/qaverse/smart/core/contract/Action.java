package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public interface Action {

    ActionType getType();

    ActionResult execute(
            ActionRequest request
    );
}