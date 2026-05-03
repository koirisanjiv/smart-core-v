package com.qaverse.smart.core.context;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class TestContext {

    private WebDriver driver;

    private final Map<String, Object> data = new HashMap<>();

    // ---------------- Driver ----------------
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    // ---------------- Generic Context ----------------
    public void set(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) data.get(key);
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }
}