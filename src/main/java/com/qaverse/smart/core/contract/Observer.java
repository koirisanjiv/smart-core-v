package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.model.ObservationResult;
import com.qaverse.smart.core.observer.ObservationType;

public interface Observer {

    ObservationType getType();

    ObservationResult observe(
            ExecutionContext context
    );
}