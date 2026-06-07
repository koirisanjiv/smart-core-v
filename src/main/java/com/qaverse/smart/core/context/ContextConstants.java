package com.qaverse.smart.core.context;

public final class ContextConstants {

    private ContextConstants() {
    }

    public static final String EXECUTION_ID =
            ContextKey.EXECUTION_ID.name();

    public static final String CURRENT_ATTEMPT =
            ContextKey.CURRENT_ATTEMPT.name();

    public static final String MAX_ATTEMPTS =
            ContextKey.MAX_ATTEMPTS.name();
}