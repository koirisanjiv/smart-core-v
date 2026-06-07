package com.qaverse.smart.core.event;

public final class ActionSucceededEvent
        extends ExecutionEvent {

    private ActionSucceededEvent(
            Builder builder) {

        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

        @Override
        public ActionSucceededEvent build() {

            eventType(
                    EventType.EXECUTION_SUCCEEDED
            );

            validate();

            return new ActionSucceededEvent(
                    this
            );
        }
    }
}