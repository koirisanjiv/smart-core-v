package com.qaverse.smart.Retry;

import com.qaverse.smart.Failure.FailureSeverity;
import com.qaverse.smart.Failure.FailureType;

public class RetryDecisionManager {

    public static boolean shouldRetry(FailureType type, FailureSeverity severity) {

        if (severity == FailureSeverity.CRITICAL) {
			return false;
		}

        switch (type) {

            case STALE_ELEMENT:
            case ELEMENT_CLICK_INTERCEPTED:
            case ELEMENT_NOT_CLICKABLE:
            case TIMEOUT:
                return true;

            case ASSERTION_FAILED:
            case INVALID_INPUT:
                return false;

            default:
                return false;
        }
    }

    public static int getMaxRetries(FailureType type) {

        switch (type) {

            case STALE_ELEMENT:
                return 3;

            case ELEMENT_CLICK_INTERCEPTED:
                return 2;

            case TIMEOUT:
                return 2;

            default:
                return 1;
        }
    }
}