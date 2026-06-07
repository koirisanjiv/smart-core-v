package com.qaverse.smart.core.recovery;

public final class RecoveryMessages {

    private RecoveryMessages() {
    }

    public static final String FAILURE_CONTEXT_NULL =
            "FailureContext cannot be null";

    public static final String EXECUTION_CONTEXT_NULL =
            "ExecutionContext cannot be null";

    public static final String RECOVERY_CONTEXT_NULL =
            "RecoveryContext cannot be null";

    public static final String RECOVERY_FAILED =
            "Recovery failed";

    public static final String NO_RECOVERY_STRATEGY_FOUND =
            "No recovery strategy found";

    public static final String PAGE_REFRESHED =
            "Page refreshed";

    public static final String DOM_STABILIZED =
            "DOM stabilized";

    public static final String TIMEOUT_RECOVERED =
            "Timeout recovered";

    public static final String STALE_ELEMENT_RECOVERED =
            "Stale element recovered";

    public static final String CLICK_INTERCEPTION_RECOVERED =
            "Click interception recovered";

    public static final String OVERLAY_DISAPPEARED =
            "Overlay disappeared";

    public static final String ELEMENT_VISIBILITY_RECOVERED =
            "Element visibility recovered";

    public static final String NO_RECOVERY_POSSIBLE =
            "No recovery possible";
}