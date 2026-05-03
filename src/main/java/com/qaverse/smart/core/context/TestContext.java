package com.qaverse.smart.core.context;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;
    private final Map<String, Object> data = new HashMap<>();
    private String testName;

    // ===== DRIVER =====
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // ===== GENERIC CONTEXT =====
    public void set(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) data.get(key);
    }

    // ===== TEST INFO =====
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}