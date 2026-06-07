package com.qaverse.smart.core.context;

import org.openqa.selenium.WebDriver;

public final class ContextInitializer {

    private ContextInitializer() {
    }

    public static ExecutionContext initialize(
            WebDriver driver) {

        ExecutionContext context =
                new ContextBuilder()
                        .driver(driver)
                        .build();

        ContextManager.set(context);

        return context;
    }
}