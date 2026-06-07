package com.qaverse.smart.core.engine;

import java.util.List;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.exception.ObserverException;
import com.qaverse.smart.core.model.ObservationResult;
import com.qaverse.smart.core.registry.ObserverRegistry;

public class ObservationEngine {

    public void observe(
            ExecutionContext context) {

        List<Observer> observers =
                ObserverRegistry.getAll();

        for (Observer observer : observers) {

        	  System.out.println(
        	            "Running Observer = "
        	            + observer.getClass().getSimpleName()
        	    );
        	  
            ObservationResult result =
                    observer.observe(context);

            if (!result.isSuccess()) {

            	throw new ObserverException(
            	        result.getMessage()
            	);
            }
        }
    }
}