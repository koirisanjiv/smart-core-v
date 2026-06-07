package com.qaverse.smart.core.event;

import com.qaverse.smart.core.failure.FailureContext;

public final class ActionFailedEvent
        extends ExecutionEvent {

    private final FailureContext failureContext;

    private ActionFailedEvent(
            Builder builder) {

        super(builder);
        this.failureContext =
                builder.failureContext;
    }

    public FailureContext getFailureContext() {
        return failureContext;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

        private FailureContext failureContext;

        public Builder failureContext(
                FailureContext failureContext) {

            this.failureContext =
                    failureContext;
            return this;
        }

        @Override
        public ActionFailedEvent build() {

            if (failureContext == null) {
                throw new IllegalStateException(
                        "FailureContext cannot be null"
                );
            }

            eventType(
                    EventType.EXECUTION_FAILED
            );

            validate();

            return new ActionFailedEvent(
                    this
            );
        }
    }
}