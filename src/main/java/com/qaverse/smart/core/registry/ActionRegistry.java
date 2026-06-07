package com.qaverse.smart.core.registry;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.qaverse.smart.core.action.ActionType;
import com.qaverse.smart.core.contract.Action;

public final class ActionRegistry {

    private static final ActionRegistry INSTANCE =
            new ActionRegistry();

    private final Map<ActionType, Action> actions =
            new ConcurrentHashMap<>();

    private ActionRegistry() {

        RegistryInitializer.initialize();
    }

    public static ActionRegistry getInstance() {
        return INSTANCE;
    }

    public void register(
            Action action) {

        if (action == null) {
            throw new RegistryException(
                    RegistryMessage.ACTION_NULL.getMessage()
            );
        }

        actions.put(
                action.getType(),
                action
        );
    }

    public Action get(
            ActionType type) {

        Action action =
                actions.get(type);

        if (action == null) {
            throw new RegistryException(
                    RegistryMessage.ACTION_NOT_REGISTERED.getMessage()
                            + type
            );
        }

        return action;
    }

    public Collection<Action> getAll() {
        return actions.values();
    }

    public void clear() {
        actions.clear();
    }
}