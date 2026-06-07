package com.qaverse.smart.core.listener;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import com.qaverse.smart.core.contract.ExecutionListener;
import com.qaverse.smart.core.event.ExecutionEvent;

public final class ListenerManager {

    private static final List<ListenerRegistration>
            REGISTRATIONS =
            new CopyOnWriteArrayList<>();

    private ListenerManager() {
    }

    public static void register(
            String name,
            ExecutionListener listener) {

        if (listener == null) {
            return;
        }

        unregister(name);

        REGISTRATIONS.add(
                new ListenerRegistration(
                        name,
                        listener
                )
        );
    }

    public static void unregister(
            String name) {

        REGISTRATIONS.removeIf(
                registration ->
                Objects.equals(
                        registration.getName(),
                        name
                )
        );
    }

    public static void clear() {

        REGISTRATIONS.clear();
    }

    public static List<ExecutionListener> getListeners() {

        return REGISTRATIONS.stream()
                .map(
                        ListenerRegistration::getListener
                )
                .collect(
                        Collectors.toUnmodifiableList()
                );
    }

    public static List<ListenerRegistration>
    getRegistrations() {

        return List.copyOf(
                REGISTRATIONS
        );
    }

    public static void dispatch(
            ExecutionEvent event) {

        for (ListenerRegistration registration :
                REGISTRATIONS) {

            try {

                ListenerDispatcher.dispatch(
                        registration.getListener(),
                        event
                );

            } catch (Exception ignored) {

                // Listener failures must never
                // break Smart Core execution
            }
        }
    }
}