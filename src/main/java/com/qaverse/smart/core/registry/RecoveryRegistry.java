package com.qaverse.smart.core.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qaverse.smart.core.recovery.BaseRecoveryStrategy;

public final class RecoveryRegistry {

    private static final List<BaseRecoveryStrategy> STRATEGIES =
            new ArrayList<>();

    private RecoveryRegistry() {
    }

    public static void register(
            BaseRecoveryStrategy strategy) {

        if (strategy == null) {
            throw new RegistryException(
                    "Recovery strategy cannot be null"
            );
        }

        STRATEGIES.add(strategy);
    }

    public static List<BaseRecoveryStrategy> getAll() {

        return Collections.unmodifiableList(
                STRATEGIES
        );
    }

    public static void clear() {
        STRATEGIES.clear();
    }
}