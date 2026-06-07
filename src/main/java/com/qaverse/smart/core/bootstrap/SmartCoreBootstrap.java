package com.qaverse.smart.core.bootstrap;

import com.qaverse.smart.core.registry.RegistryInitializer;

public final class SmartCoreBootstrap {

    private static volatile boolean initialized;

    private SmartCoreBootstrap() {
    }

    public static synchronized void initialize() {

        if (initialized) {
            return;
        }

        RegistryInitializer.initialize();

        initialized = true;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}