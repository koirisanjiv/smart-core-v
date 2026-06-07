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
                        "Listener name cannot be null"
                );

        this.listener =
                Objects.requireNonNull(
                        listener,
                        "Listener cannot be null"
                );
    }

    public String getName() {
        return name;
    }

    public ExecutionListener getListener() {
        return listener;
    }
}