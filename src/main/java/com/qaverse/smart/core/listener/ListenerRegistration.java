package com.qaverse.smart.core.listener;

import java.util.Objects;

import com.qaverse.smart.core.contract.ExecutionListener;

public final class ListenerRegistration {

    private final String name;

    private final ExecutionListener listener;

    public ListenerRegistration(
            String name,
            ExecutionListener listener) {

        this.name =
                Objects.requireNonNull(
                        name,
                        ListenerMessages.LISTENER_NAME_NULL
                );

        this.listener =
                Objects.requireNonNull(
                        listener,
                        ListenerMessages.LISTENER_NULL
                );
    }

    public String getName() {
        return name;
    }

    public ExecutionListener getListener() {
        return listener;
    }
}