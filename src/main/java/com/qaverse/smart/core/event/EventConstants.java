package com.qaverse.smart.core.event;

public final class EventConstants {

    private EventConstants() {
    }

    public static final String ACTION_STARTED =
            "Action execution started";

    public static final String ACTION_SUCCEEDED =
            "Action execution succeeded";

    public static final String ACTION_FAILED =
            "Action execution failed";

    public static final String ACTION_RECOVERED =
            "Action recovered";

    public static final String ACTION_RETRY =
            "Retry initiated";
}