package com.qaverse.smart.core.registry;

public enum RegistryMessage {

    ACTION_NULL(
            "Action cannot be null"
    ),

    ACTION_NOT_REGISTERED(
            "No action registered for: "
    ),

    RECOVERY_NULL(
            "Recovery strategy cannot be null"
    ),

    OBSERVER_NULL(
            "Observer cannot be null"
    );

    private final String message;

    RegistryMessage(
            String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}