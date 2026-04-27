package com.qaverse.smart.Analytics;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.qaverse.smart.Logging.LoggerManager;

public class SmartAnalyticsRunner {

    public static void run(String testName, boolean openDashboard) {
        // 1. Write JSON
        AnalyticsWriter.writeToFile();

        // 2. Load all results
        List<StepResult> steps = AnalyticsAggregator.loadAll();

        // 3. Generate stats
        Map<String, Integer> retryStats =
                AnalyticsAggregator.getRetryStats(steps);

        Map<String, Double> avgTime =
                AnalyticsAggregator.getAvgDuration(steps);

        // 4. Print summary
        printSummary(retryStats, avgTime);

        // 5. Open dashboard (optional)
        if (openDashboard) {
            openDashboard();
        }
    }

    private static void printSummary(Map<String, Integer> retryStats,
                                     Map<String, Double> avgTime) {

       LoggerManager.info("\n===== SMART ANALYTICS SUMMARY =====");

       LoggerManager.info("\nRetry Stats:");
        retryStats.forEach((k, v) ->
               LoggerManager.info(k + " → " + v + " retries"));

       LoggerManager.info("\nAverage Execution Time:");
        avgTime.forEach((k, v) ->
               LoggerManager.info(k + " → " + String.format("%.2f ms", v)));
    }

    private static void openDashboard() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            String url = "http://localhost:8080/dashboard.html";

            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start " + url);
            } else {
                Runtime.getRuntime().exec("xdg-open " + url);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}