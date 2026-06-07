package com.qaverse.smart.core.observer;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public class AjaxObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.AJAX;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        return ObservationResult.success(
                ObservationType.AJAX.name()
        );
    }
}