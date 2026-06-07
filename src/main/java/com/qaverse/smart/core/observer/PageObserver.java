package com.qaverse.smart.core.observer;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public final class PageObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.PAGE;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        WebDriver driver =
                context.getDriver();

        try {

            String url =
                    driver.getCurrentUrl();

            if (url == null
                    || url.isBlank()) {

                return ObservationResult.failure(
                        "Current URL is empty"
                );
            }

            return ObservationResult.success(
                    ObservationMessages.PAGE_READY
            );

        } catch (Exception ex) {

            return ObservationResult.failure(
                    ex.getMessage()
            );
        }
    }
}