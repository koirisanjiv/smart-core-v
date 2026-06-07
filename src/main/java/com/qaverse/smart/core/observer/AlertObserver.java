package com.qaverse.smart.core.observer;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public final class AlertObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.ALERT;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        WebDriver driver =
                context.getDriver();

        try {

            driver.switchTo().alert();

            return ObservationResult.failure(
                    "Unexpected alert present"
            );

        } catch (NoAlertPresentException ex) {

            return ObservationResult.success(
                    ObservationMessages.ALERT_NOT_PRESENT
            );
        }
    }
}