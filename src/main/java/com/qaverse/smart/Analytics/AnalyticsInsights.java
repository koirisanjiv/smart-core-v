package com.qaverse.smart.Analytics;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AnalyticsInsights {

    public static Set<String> getFlakySteps(List<StepResult> steps) {

        Map<String, Long> retryCount = steps.stream()
                .filter(s -> "RETRY".equals(s.getStatus()))
                .collect(Collectors.groupingBy(
                        StepResult::getStepName,
                        Collectors.counting()
                ));

        return retryCount.entrySet().stream()
                .filter(e -> e.getValue() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public static Map<String, Long> getFailureDistribution(List<StepResult> steps) {

        return steps.stream()
                .filter(s -> "FAIL".equals(s.getStatus()))
                .collect(Collectors.groupingBy(
                        StepResult::getFailureType,
                        Collectors.counting()
                ));
    }

    public static List<String> getSlowSteps(List<StepResult> steps) {

        return steps.stream()
                .filter(s -> s.getDuration() > 3000)
                .map(StepResult::getStepName)
                .distinct()
                .toList();
    }

    public static double getStabilityScore(List<StepResult> steps) {

        if (steps.isEmpty()) {
			return 0;
		}

        long failures = steps.stream()
                .filter(s -> "FAIL".equals(s.getStatus()))
                .count();

        return ((double)(steps.size() - failures) / steps.size()) * 100;
    }
}