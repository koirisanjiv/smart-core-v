package com.qaverse.smart.core.model;

import java.time.Instant;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.execution.ExecutionStatus;

public final class ActionResult {

    private final ActionType actionType;

    private final ExecutionStatus status;

    private final String message;

    private final long durationMs;

    private final Instant timestamp;

    private final Throwable throwable;

    private ActionResult(Builder builder) {

        this.actionType = builder.actionType;
        this.status = builder.status;
        this.message = builder.message;
        this.durationMs = builder.durationMs;
        this.timestamp = builder.timestamp;
        this.throwable = builder.throwable;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public static ActionResult success(
            ActionType actionType,
            String message,
            long durationMs) {

        return ActionResult.builder()
                .actionType(actionType)
                .status(ExecutionStatus.PASSED)
                .message(message)
                .durationMs(durationMs)
                .timestamp(Instant.now())
                .build();
    }

    public static ActionResult failure(
            ActionType actionType,
            String message,
            Throwable throwable) {

        return ActionResult.builder()
                .actionType(actionType)
                .status(ExecutionStatus.FAILED)
                .message(message)
                .throwable(throwable)
                .timestamp(Instant.now())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private ActionType actionType;

        private ExecutionStatus status;

        private String message;

        private long durationMs;

        private Instant timestamp;

        private Throwable throwable;

        public Builder actionType(
                ActionType actionType) {

            this.actionType = actionType;
            return this;
        }

        public Builder status(
                ExecutionStatus status) {

            this.status = status;
            return this;
        }

        public Builder message(
                String message) {

            this.message = message;
            return this;
        }

        public Builder durationMs(
                long durationMs) {

            this.durationMs = durationMs;
            return this;
        }

        public Builder timestamp(
                Instant timestamp) {

            this.timestamp = timestamp;
            return this;
        }

        public Builder throwable(
                Throwable throwable) {

            this.throwable = throwable;
            return this;
        }

        public ActionResult build() {

            return new ActionResult(this);
        }
    }
}