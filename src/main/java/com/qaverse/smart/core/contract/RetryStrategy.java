package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.failure.FailureContext;
import com.qaverse.smart.core.model.RetryResult;

public interface RetryStrategy {

    RetryResult evaluate(
            FailureContext failure,
            int currentAttempt
    );

}