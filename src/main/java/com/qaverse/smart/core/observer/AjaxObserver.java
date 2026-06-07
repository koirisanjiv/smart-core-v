package com.qaverse.smart.core.observer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.contract.Observer;
import com.qaverse.smart.core.model.ObservationResult;

public final class AjaxObserver
        implements Observer {

    @Override
    public ObservationType getType() {
        return ObservationType.AJAX;
    }

    @Override
    public ObservationResult observe(
            ExecutionContext context) {

        WebDriver driver =
                context.getDriver();

        try {

            Object result =
                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "return window.jQuery != undefined ? jQuery.active : 0;"
                            );

            long activeRequests =
                    Long.parseLong(
                            result.toString()
                    );

            if (activeRequests == 0) {

                return ObservationResult.success(
                        ObservationMessages.AJAX_COMPLETED
                );
            }

            return ObservationResult.failure(
                    "Ajax requests still running"
            );

        } catch (Exception ex) {

            return ObservationResult.success(
                    ObservationMessages.AJAX_COMPLETED
            );
        }
    }
}