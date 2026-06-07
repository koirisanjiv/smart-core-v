package com.qaverse.smart.core.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;

public final class ExecutionContext {

    private final WebDriver driver;

    private final Map<String, Object> attributes =
            new ConcurrentHashMap<>();

    public ExecutionContext(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null"
            );
        }

        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void put(String key, Object value) {
        attributes.put(key, value);
    }

    public Object get(String key) {
        return attributes.get(key);
    }

    public <T> T get(String key, Class<T> type) {

        Object value = attributes.get(key);

        if (value == null) {
            return null;
        }

        return type.cast(value);
    }

    public boolean contains(String key) {
        return attributes.containsKey(key);
    }

    public void remove(String key) {
        attributes.remove(key);
    }

    public void clear() {
        attributes.clear();
    }

    public Map<String, Object> getAttributes() {
        return Map.copyOf(attributes);
    }
}