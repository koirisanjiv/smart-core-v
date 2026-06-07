package com.qaverse.smart.core.registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.qaverse.smart.core.recovery.BaseRecoveryStrategy;

public final class RecoveryRegistry {

    private static final List<BaseRecoveryStrategy>
            STRATEGIES =
            new CopyOnWriteArrayList<>();

    private RecoveryRegistry() {
    }

    public static void register(
            BaseRecoveryStrategy strategy) {

        if (strategy == null) {
            return;
        }

        STRATEGIES.add(strategy);
    }

    public static List<BaseRecoveryStrategy> getAll() {

        return List.copyOf(
                STRATEGIES
        );
    }

    public static void clear() {

        STRATEGIES.clear();
    }
}