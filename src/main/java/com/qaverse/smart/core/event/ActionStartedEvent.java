package com.qaverse.smart.core.event;

public final class ActionStartedEvent
        extends ExecutionEvent {

    private ActionStartedEvent(
            Builder builder) {

        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

    	@Override
    	public ActionStartedEvent build() {

    	    validate();

    	    eventType(
    	            EventType.EXECUTION_STARTED
    	    );

    	    return new ActionStartedEvent(
    	            this
    	    );
    	}
    }
}