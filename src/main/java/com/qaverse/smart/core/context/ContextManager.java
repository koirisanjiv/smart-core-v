package com.qaverse.smart.core.context;

public final class ContextManager {

    private static final ThreadLocal<ExecutionContext>
            CONTEXT = new ThreadLocal<>();

    private ContextManager() {
    }

    public static void set(
            ExecutionContext context) {

        CONTEXT.set(context);
    }

    public static ExecutionContext get() {

        ExecutionContext context = CONTEXT.get();

        if (context == null) {
            throw new IllegalStateException(
                    "ExecutionContext not initialized"
            );
        }

        return context;
    }

    public static void remove() {
        CONTEXT.remove();
    }
}