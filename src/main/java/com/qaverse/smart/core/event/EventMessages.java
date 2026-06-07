package com.qaverse.smart.core.event;

public final class EventMessages {

    private EventMessages() {
    }

    public static final String EVENT_TYPE_NULL =
            "EventType cannot be null";

    public static final String TIMESTAMP_NULL =
            "Timestamp cannot be null";

    public static final String FAILURE_CONTEXT_NULL =
            "FailureContext cannot be null";

    public static final String RECOVERY_TYPE_NULL =
            "RecoveryType cannot be null";

    public static final String VALIDATOR_TYPE_NULL =
            "ValidatorType cannot be null";

    public static final String ATTEMPT_INVALID =
            "Attempt must be greater than zero";

    public static final String FAILURE_TYPE_NULL =
            "FailureType cannot be null";
}