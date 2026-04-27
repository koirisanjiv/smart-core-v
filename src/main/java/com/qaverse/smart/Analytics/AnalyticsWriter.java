package com.qaverse.smart.Analytics;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaverse.smart.Logging.LoggerManager;

public class AnalyticsWriter {

    private static final String OUTPUT_PATH = "analytics/latest.json";

    public static void writeToFile() {

        try {
            Map<String, List<StepResult>> allData = AnalyticsManager.getAllResults();

            if (allData == null || allData.isEmpty()) {
                System.out.println("No analytics data to write");
                return;
            }

            // 🔥 Flatten Map → List
            List<StepResult> results = allData.values()
                    .stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();

            File file = new File(OUTPUT_PATH);
            file.getParentFile().mkdirs();

            // 🔥 Write to temp file first (safe write)
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(tempFile, results);

            // 🔥 Atomic replace
            Files.move(
                    tempFile.toPath(),
                    file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            LoggerManager.info("Analytics written to: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}