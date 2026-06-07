package com.qaverse.smart.core.listener;

import com.qaverse.smart.core.contract.ExecutionListener;
import com.qaverse.smart.core.event.ActionFailedEvent;
import com.qaverse.smart.core.event.ActionRecoveredEvent;
import com.qaverse.smart.core.event.ActionRetryEvent;
import com.qaverse.smart.core.event.ActionStartedEvent;
import com.qaverse.smart.core.event.ActionSucceededEvent;
import com.qaverse.smart.core.event.ExecutionEvent;
import com.qaverse.smart.core.event.ValidationFailedEvent;

public final class ListenerDispatcher {

    private ListenerDispatcher() {
    }

    public static void dispatch(
            ExecutionListener listener,
            ExecutionEvent event) {

        if (event instanceof ActionStartedEvent) {

            listener.onStart(event);

        } else if (event instanceof ActionSucceededEvent) {

            listener.onSuccess(event);

        } else if (event instanceof ActionRetryEvent) {

            listener.onRetry(event);

        } else if (event instanceof ActionRecoveredEvent) {

            listener.onRecovery(event);

        } else if (event instanceof ActionFailedEvent) {

            listener.onFailure(event);

        } else if (event instanceof ValidationFailedEvent) {

            listener.onFailure(event);
        }
    }
}