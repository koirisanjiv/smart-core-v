package com.qaverse.smart.core.contract;

import com.qaverse.smart.core.event.ExecutionEvent;

public interface ExecutionListener {

    default void onStart(
            ExecutionEvent event
    ) {}

    default void onSuccess(
            ExecutionEvent event
    ) {}

    default void onRetry(
            ExecutionEvent event
    ) {}

    default void onRecovery(
            ExecutionEvent event
    ) {}

    default void onFailure(
            ExecutionEvent event
    ) {}

}