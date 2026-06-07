package com.qaverse.smart.core.context;

import org.openqa.selenium.WebDriver;

public final class ContextBuilder {

    private WebDriver driver;

    public ContextBuilder driver(
            WebDriver driver) {

        this.driver = driver;
        return this;
    }

    public ExecutionContext build() {

        return new ExecutionContext(driver);
    }
}