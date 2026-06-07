package com.qaverse.smart.core.listener;

import com.qaverse.smart.core.contract.ExecutionListener;
import com.qaverse.smart.core.event.ExecutionEvent;

public abstract class BaseExecutionListener
        implements ExecutionListener {

    @Override
    public void onStart(
            ExecutionEvent event) {
    }

    @Override
    public void onSuccess(
            ExecutionEvent event) {
    }

    @Override
    public void onRetry(
            ExecutionEvent event) {
    }

    @Override
    public void onRecovery(
            ExecutionEvent event) {
    }

    @Override
    public void onFailure(
            ExecutionEvent event) {
    }
}