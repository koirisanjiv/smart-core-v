package com.qaverse.smart.core.event;

import java.time.Instant;
import java.util.UUID;

import com.qaverse.smart.core.action.ActionType;

public abstract class ExecutionEvent {

    private final String eventId;
    private final EventType eventType;
    private final ActionType actionType;
    private final Instant timestamp;
    private final String message;

    protected ExecutionEvent(
            Builder builder) {

        this.eventId = builder.eventId;
        this.eventType = builder.eventType;
        this.actionType = builder.actionType;
        this.timestamp = builder.timestamp;
        this.message = builder.message;
    }

    public String getEventId() {
        return eventId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public abstract static class Builder {

        private String eventId =
                UUID.randomUUID().toString();

        private EventType eventType;

        private ActionType actionType;

        private Instant timestamp =
                Instant.now();

        private String message;

        public Builder eventType(
                EventType eventType) {

            this.eventType = eventType;
            return this;
        }

        public Builder actionType(
                ActionType actionType) {

            this.actionType = actionType;
            return this;
        }

        public Builder timestamp(
                Instant timestamp) {

            this.timestamp = timestamp;
            return this;
        }

        public Builder message(
                String message) {

            this.message = message;
            return this;
        }

        protected void validate() {

            if (eventType == null) {
                throw new IllegalStateException(
                        "EventType cannot be null"
                );
            }
        }

        public abstract ExecutionEvent build();
    }
}