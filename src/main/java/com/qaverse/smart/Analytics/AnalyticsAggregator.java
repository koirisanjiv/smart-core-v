package com.qaverse.smart.Analytics;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnalyticsAggregator {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<StepResult> loadAll() {
        List<StepResult> allSteps = new ArrayList<>();

        File folder = new File("analytics");

        if (!folder.exists()) {
			return allSteps;
		}

        for (File file : folder.listFiles((dir, name) -> name.endsWith(".json"))) {
            try {
                List<StepResult> steps =
                        mapper.readValue(file, new TypeReference<List<StepResult>>() {});
                allSteps.addAll(steps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return allSteps;
    }

    public static Map<String, Integer> getRetryStats(List<StepResult> steps) {

        Map<String, Integer> retryCount = new HashMap<>();

        for (StepResult s : steps) {
            if ("RETRY".equals(s.getStatus())) {
                retryCount.merge(s.getStepName(), 1, Integer::sum);
            }
        }

        return retryCount;
    }

    public static Map<String, Double> getAvgDuration(List<StepResult> steps) {

        return steps.stream()
                .filter(s -> s.getDuration() > 0)
                .collect(Collectors.groupingBy(
                        StepResult::getStepName,
                        Collectors.averagingLong(StepResult::getDuration)
                ));
    }
}
