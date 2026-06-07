package com.qaverse.smart.core.registry;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.action.ClickAction;
import com.qaverse.smart.core.action.InputAction;
import com.qaverse.smart.core.observer.AjaxObserver;
import com.qaverse.smart.core.observer.AlertObserver;
import com.qaverse.smart.core.observer.DomObserver;
import com.qaverse.smart.core.observer.ElementObserver;
import com.qaverse.smart.core.observer.EnvironmentObserver;
import com.qaverse.smart.core.observer.LoaderObserver;
import com.qaverse.smart.core.observer.OverlayObserver;
import com.qaverse.smart.core.observer.PageObserver;
import com.qaverse.smart.core.recovery.InterceptedRecovery;
import com.qaverse.smart.core.recovery.NoOpRecovery;
import com.qaverse.smart.core.recovery.RefreshPageRecovery;
import com.qaverse.smart.core.recovery.StaleRecovery;
import com.qaverse.smart.core.recovery.TimeoutRecovery;
import com.qaverse.smart.core.recovery.VisibilityRecovery;
import com.qaverse.smart.core.recovery.WaitForDomRecovery;

public final class RegistryInitializer {

    private static volatile boolean initialized;

    private RegistryInitializer() {
    }

    public static synchronized void initialize() {

        if (initialized) {
            return;
        }

        try {

            registerActions();

            registerObservers();

            registerRecoveries();

            validate();

            initialized = true;

        } catch (Exception ex) {

            initialized = false;

            throw new RegistryException(
                    "Failed to initialize Smart Core registries",
                    ex
            );
        }
    }

    private static void registerActions() {

        ActionRegistry registry =
                ActionRegistry.getInstance();

        registry.register(
                new ClickAction()
        );

        registry.register(
                new InputAction()
        );
    }

    private static void registerObservers() {

        ObserverRegistry.register(
                new LoaderObserver()
        );

        ObserverRegistry.register(
                new DomObserver()
        );

        ObserverRegistry.register(
                new AlertObserver()
        );

        ObserverRegistry.register(
                new AjaxObserver()
        );

        ObserverRegistry.register(
                new OverlayObserver()
        );

        ObserverRegistry.register(
                new ElementObserver()
        );

        ObserverRegistry.register(
                new PageObserver()
        );

        ObserverRegistry.register(
                new EnvironmentObserver()
        );
    }

    private static void registerRecoveries() {

        RecoveryRegistry.register(
                new StaleRecovery()
        );

        RecoveryRegistry.register(
                new TimeoutRecovery()
        );

        RecoveryRegistry.register(
                new InterceptedRecovery()
        );

        RecoveryRegistry.register(
                new VisibilityRecovery()
        );

        RecoveryRegistry.register(
                new WaitForDomRecovery()
        );

        RecoveryRegistry.register(
                new RefreshPageRecovery()
        );

        RecoveryRegistry.register(
                new NoOpRecovery()
        );
    }

    private static void validate() {

        ActionRegistry actions =
                ActionRegistry.getInstance();

        actions.get(
                ActionType.CLICK
        );

        actions.get(
                ActionType.INPUT
        );

        if (ObserverRegistry.getAll().isEmpty()) {

            throw new RegistryException(
                    "No observers registered"
            );
        }

        if (RecoveryRegistry.getAll().isEmpty()) {

            throw new RegistryException(
                    "No recovery strategies registered"
            );
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}