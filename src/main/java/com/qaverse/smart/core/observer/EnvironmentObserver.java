package com.qaverse.smart.core.observer;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public final class EnvironmentObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.ENVIRONMENT;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        WebDriver driver =
                context.getDriver();

        if (driver == null) {

            return ObservationResult.failure(
                    ObservationMessages.DRIVER_NOT_AVAILABLE
            );
        }

        try {

            driver.getWindowHandle();

            return ObservationResult.success(
                    ObservationMessages.ENVIRONMENT_READY
            );

        } catch (Exception ex) {

            return ObservationResult.failure(
                    ex.getMessage()
            );
        }
    }
}