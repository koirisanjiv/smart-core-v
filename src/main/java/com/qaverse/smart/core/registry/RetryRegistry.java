package com.qaverse.smart.core.registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.qaverse.smart.core.contract.RetryStrategy;

public final class RetryRegistry {

    private static final List<RetryStrategy>
            STRATEGIES =
            new CopyOnWriteArrayList<>();

    private RetryRegistry() {
    }

    public static void register(
            RetryStrategy strategy) {

        STRATEGIES.add(strategy);
    }

    public static List<RetryStrategy> getAll() {

        return List.copyOf(STRATEGIES);
    }

    public static void clear() {

        STRATEGIES.clear();
    }
}