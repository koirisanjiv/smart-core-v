package com.qaverse.smart.core.engine.runtime;

public final class EngineManager {

    private static AutomationEngine engine;

    private EngineManager() {
    }

    public static void setEngine(
            AutomationEngine automationEngine) {

        engine = automationEngine;
    }

    public static AutomationEngine getEngine() {

        if (engine == null) {

            throw new IllegalStateException(
                    "Automation Engine not initialized"
            );
        }

        return engine;
    }
}