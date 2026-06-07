package com.qaverse.smart.core.model;

import java.util.Objects;

import com.qaverse.smart.core.validation.ValidationMessages;
import com.qaverse.smart.core.validation.ValidatorType;

public final class ValidationResult {

    private final boolean valid;

    private final ValidatorType validatorType;

    private final String message;

    public ValidationResult(
            boolean valid,
            ValidatorType validatorType,
            String message) {

        this.valid = valid;

        this.validatorType =
                Objects.requireNonNull(
                        validatorType,
                        ValidationMessages.VALIDATOR_TYPE_NULL
                );

        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public ValidatorType getValidatorType() {
        return validatorType;
    }

    public String getMessage() {
        return message;
    }

    public static ValidationResult success(
            ValidatorType validatorType,
            String message) {

        return new ValidationResult(
                true,
                validatorType,
                message
        );
    }

    public static ValidationResult failure(
            ValidatorType validatorType,
            String message) {

        return new ValidationResult(
                false,
                validatorType,
                message
        );
    }

    public static final ValidationResult VALID =
            new ValidationResult(
                    true,
                    ValidatorType.NONE,
                    ValidationMessages.VALIDATION_SUCCESS
            );

    public static final ValidationResult INVALID =
            new ValidationResult(
                    false,
                    ValidatorType.NONE,
                    ValidationMessages.VALIDATION_FAILED
            );
}