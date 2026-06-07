package com.qaverse.smart.core.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qaverse.smart.core.contract.Validator;

public final class ValidatorRegistry {

    private static final List<Validator> VALIDATORS =
            new ArrayList<>();

    private ValidatorRegistry() {
    }

    public static void register(
            Validator validator) {

        if (validator == null) {
            return;
        }

        VALIDATORS.add(validator);
    }

    public static List<Validator> getAll() {

        return Collections.unmodifiableList(
                VALIDATORS
        );
    }

    public static void clear() {
        VALIDATORS.clear();
    }
}