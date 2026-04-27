package com.qaverse.smart.Analytics;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaverse.smart.report.AllureSafe;

public class AnalyticsAllureAdapter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void attach(String testName) {
        try {
            List<StepResult> results =
                    AnalyticsManager.getAllResults().get(testName);

            if (results == null || results.isEmpty()) {
				return;
			}

            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(results);

            AllureSafe.addAttachment(
                    "Step Analytics - " + testName,
                    "application/json",
                    new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)),
                    ".json"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}