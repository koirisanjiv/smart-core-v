package com.qaverse.smart.Analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsManager {

    // Per Test (Thread-safe)
    private static final ThreadLocal<List<StepResult>> stepResults =
            ThreadLocal.withInitial(ArrayList::new);

    // Global storage (All tests)
    private static final Map<String, List<StepResult>> testResults =
            new ConcurrentHashMap<>();

    // ===== RECORD =====
    public static void record(StepResult result) {
        stepResults.get().add(result);
    }

    // ===== FLUSH AFTER TEST =====
    public static void flush(String testName) {
        testResults.put(testName, new ArrayList<>(stepResults.get()));
        stepResults.remove();
    }

    // ===== GET RESULTS =====
    public static Map<String, List<StepResult>> getAllResults() {
        return testResults;
    }
}