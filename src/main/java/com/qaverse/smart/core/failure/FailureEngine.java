package com.qaverse.smart.core.failure;

public final class FailureEngine {

    public FailureDecision process(
            FailureContext context) {

        FailureClassificationResult result =
                FailureAnalyzer.analyze(
                        context.getFailureType()
                );

        return new FailureDecision(
                result.getDisposition(),
                context.getMessage()
        );
    }
}