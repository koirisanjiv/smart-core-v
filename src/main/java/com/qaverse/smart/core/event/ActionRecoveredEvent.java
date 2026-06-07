package com.qaverse.smart.core.event;

import com.qaverse.smart.core.recovery.RecoveryType;

public final class ActionRecoveredEvent
        extends ExecutionEvent {

    private final RecoveryType recoveryType;

    private ActionRecoveredEvent(
            Builder builder) {

        super(builder);
        this.recoveryType =
                builder.recoveryType;
    }

    public RecoveryType getRecoveryType() {
        return recoveryType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

        private RecoveryType recoveryType;

        public Builder recoveryType(
                RecoveryType recoveryType) {

            this.recoveryType = recoveryType;
            return this;
        }

        @Override
        public ActionRecoveredEvent build() {
        	
        	  if (recoveryType == null) {
        	        throw new IllegalStateException(
        	                "recoveryType cannot be null"
        	        );
        	    }

            eventType(
                    EventType.EXECUTION_RECOVERED
            );

            return new ActionRecoveredEvent(this);
        }
    }
}