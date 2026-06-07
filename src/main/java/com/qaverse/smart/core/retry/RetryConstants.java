package com.qaverse.smart.core.retry;

public final class RetryConstants {

    private RetryConstants() {
    }

    public static final int STALE_MAX_ATTEMPTS = 3;

    public static final int CLICK_INTERCEPTED_MAX_ATTEMPTS = 2;

    public static final int TIMEOUT_MAX_ATTEMPTS = 2;

    public static final int ELEMENT_NOT_FOUND_MAX_ATTEMPTS = 3;

    public static final int DEFAULT_MAX_ATTEMPTS = 1;
    
    public static final int MAX_ALLOWED_ATTEMPTS = 50;
}