package com.qaverse.smart.core.model;

public final class ObservationResult {

    public static final ObservationResult SUCCESS =
            new ObservationResult(
                    true,
                    "Observation successful"
            );

    public static final ObservationResult FAILURE =
            new ObservationResult(
                    false,
                    "Observation failed"
            );

    private final boolean success;

    private final String message;

    public ObservationResult(
            boolean success,
            String message) {

        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public static ObservationResult success(
            String message) {

        return new ObservationResult(
                true,
                message
        );
    }

    public static ObservationResult failure(
            String message) {

        return new ObservationResult(
                false,
                message
        );
    }

    @Override
    public String toString() {

        return "ObservationResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}