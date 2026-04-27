package com.qaverse.smart.Analytics;

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
}