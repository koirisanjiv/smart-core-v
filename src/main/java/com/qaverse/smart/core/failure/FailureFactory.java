package com.qaverse.smart.core.failure;

public final class FailureFactory {

    private FailureFactory() {
    }

    public static FailureContext create(
            Throwable throwable) {

        FailureType type =
                FailureClassifier.classify(
                        throwable
                );

        FailureClassificationResult result =
                FailureAnalyzer.analyze(
                        type
                );

        return new FailureContext(
                throwable,
                type,
                result.getCategory(),
                FailureSeverity.MEDIUM,
                throwable.getMessage()
        );
    }
}