package com.qaverse.smart.core.observer;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public class PageObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.PAGE;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        return ObservationResult.success(
                ObservationType.PAGE.name()
        );
    }
}