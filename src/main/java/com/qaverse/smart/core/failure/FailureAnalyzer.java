package com.qaverse.smart.core.failure;

public final class FailureAnalyzer {

    private FailureAnalyzer() {
    }

    public static FailureClassificationResult analyze(
            FailureType failureType) {

        switch (failureType) {

            case STALE_ELEMENT:
            case DOM_REFRESH:
            case AJAX_IN_PROGRESS:
            case LOADER_ACTIVE:
            case PAGE_NOT_READY:

                return new FailureClassificationResult(
                        failureType,
                        FailureCategory.SYNCHRONIZATION,
                        FailureDisposition.RETRY
                );

            case CLICK_INTERCEPTED:
            case FRAME_NOT_AVAILABLE:
            case WINDOW_NOT_AVAILABLE:

                return new FailureClassificationResult(
                        failureType,
                        FailureCategory.ENVIRONMENT,
                        FailureDisposition.RECOVER
                );

            case ASSERTION_FAILED:
            case VALIDATION_FAILED:
            case APPLICATION_ERROR:

                return new FailureClassificationResult(
                        failureType,
                        FailureCategory.APPLICATION,
                        FailureDisposition.FAIL_FAST
                );

            default:

                return new FailureClassificationResult(
                        failureType,
                        FailureCategory.UNKNOWN,
                        FailureDisposition.FAIL_FAST
                );
        }
    }
}