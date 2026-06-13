package com.qaverse.smart.core.engine.runtime;

import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;

public interface AutomationEngine {

    ActionResult click(
            ActionRequest request
    );

    ActionResult input(
            ActionRequest request
    );

    ActionResult checkbox(
            ActionRequest request
    );

    ActionResult radio(
            ActionRequest request
    );

    ActionResult dropdown(
            ActionRequest request
    );
}