package com.qaverse.smart.core.event;

import com.qaverse.smart.core.validation.ValidatorType;

public final class ValidationFailedEvent
        extends ExecutionEvent {

	private final ValidatorType validatorType;

    private ValidationFailedEvent(
            Builder builder) {

        super(builder);
        this.validatorType =
                builder.validatorType;
    }

    public ValidatorType getValidatorType() {
        return validatorType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends ExecutionEvent.Builder {

    	private ValidatorType validatorType;

    	public Builder validatorType(
    	        ValidatorType validatorType) {

            this.validatorType = validatorType;
            return this;
        }

        @Override
        public ValidationFailedEvent build() {

        	 if (validatorType == null) {
     	        throw new IllegalStateException(
     	                "validatorType cannot be null"
     	        );
     	    }
        	 
            eventType(
                    EventType.VALIDATION_FAILED
            );

            return new ValidationFailedEvent(this);
        }
    }
}