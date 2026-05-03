package com.qaverse.smart.core.context;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private static final ThreadLocal<String> currentTest = new ThreadLocal<>();

    public static void set(String testName) {
        currentTest.set(testName);
    }

    public static String get() {
        return currentTest.get();
    }

    public static void clear() {
        currentTest.remove();
    }
    
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}