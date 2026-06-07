package com.qaverse.smart.core.event;

import com.qaverse.smart.core.listener.ListenerManager;

public final class EventPublisher {

    private EventPublisher() {
    }

    public static void publish(
            ExecutionEvent event) {

        if (event == null) {
            return;
        }

        ListenerManager.dispatch(
                event
        );
    }
}