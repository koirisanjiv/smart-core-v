package com.qaverse.smart.core.observer;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public final class OverlayObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.OVERLAY;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        return ObservationResult.success(
                ObservationMessages.OVERLAY_NOT_PRESENT
        );
    }
}