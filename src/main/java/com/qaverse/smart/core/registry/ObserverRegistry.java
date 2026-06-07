package com.qaverse.smart.core.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qaverse.smart.core.contract.Observer;

public final class ObserverRegistry {

    private static final List<Observer> OBSERVERS =
            new ArrayList<>();

    static {
        RegistryInitializer.initialize();
    }

    private ObserverRegistry() {
    }

    public static void register(
            Observer observer) {

        if (observer != null) {
            OBSERVERS.add(observer);
        }
    }

    public static List<Observer> getAll() {

        return Collections.unmodifiableList(
                OBSERVERS
        );
    }

    public static void clear() {
        OBSERVERS.clear();
    }
}