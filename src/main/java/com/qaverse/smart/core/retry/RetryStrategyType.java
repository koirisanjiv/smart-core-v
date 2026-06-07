package com.qaverse.smart.core.retry;

public enum RetryStrategyType {

    NONE,
    FIXED_DELAY,
    EXPONENTIAL_BACKOFF,
    ADAPTIVE
}