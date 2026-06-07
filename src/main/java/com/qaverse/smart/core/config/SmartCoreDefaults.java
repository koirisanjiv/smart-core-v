package com.qaverse.smart.core.config;

public enum SmartCoreDefaults {

    DEFAULT_TIMEOUT_SECONDS(10),
    DEFAULT_RETRY_COUNT(3),
    DEFAULT_POLLING_INTERVAL_MS(250),
    DEFAULT_RETRY_DELAY_MS(500),
    DEFAULT_SLOW_ACTION_THRESHOLD_MS(3000);

    private final int value;

    SmartCoreDefaults(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}