package com.qaverse.smart.core.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private final Map<String, Object> data = new HashMap<>();

    private String testName;

    // Generic getter
    public <T> T get(String key) {
        return (T) data.get(key);
    }

    // Generic setter
    public void set(String key, Object value) {
        data.put(key, value);
    }

    // Optional helpers
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    // Test name support
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}