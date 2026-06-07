package com.qaverse.smart.core.event;

import com.qaverse.smart.core.failure.FailureType;

public final class ActionRetryEvent
        extends ExecutionEvent {

    private final int attempt;

    private final FailureType failureType;

    private ActionRetryEvent(
            Builder builder) {

        super(builder);

        this.attempt =
                builder.attempt;

        this.failureType =
                builder.failureType;
    }

    public int getAttempt() {
        return attempt;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

        private int attempt;

        private FailureType failureType;

        public Builder attempt(
                int attempt) {

            this.attempt = attempt;
            return this;
        }

        public Builder failureType(
                FailureType failureType) {

            this.failureType =
                    failureType;

            return this;
        }

        @Override
        public ActionRetryEvent build() {

            if (attempt <= 0) {

                throw new IllegalStateException(
                        "Attempt must be greater than zero"
                );
            }

            if (failureType == null) {

                throw new IllegalStateException(
                        "FailureType cannot be null"
                );
            }

            eventType(
                    EventType.EXECUTION_RETRY
            );

            return new ActionRetryEvent(
                    this
            );
        }
    }
}