package com.qaverse.smart.core.engine;

import java.util.List;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;
import com.qaverse.smart.core.registry.ObserverRegistry;

public class ObservationEngine {

    public void observe(
            ExecutionContext context) {

        List<Observer> observers =
                ObserverRegistry.getAll();

        for (Observer observer : observers) {

            ObservationResult result =
                    observer.observe(context);

            if (!result.isSuccess()) {

                throw new RuntimeException(
                        result.getMessage()
                );
            }
        }
    }
}