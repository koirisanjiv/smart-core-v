package com.qaverse.smart.core.test;

import com.qaverse.smart.core.contract.ExecutionListener;
import com.qaverse.smart.core.event.ExecutionEvent;

public final class ConsoleExecutionListener
        implements ExecutionListener {

    @Override
    public void onStart(
            ExecutionEvent event) {

        System.out.println(
                "[START] "
                        + event.getActionType()
        );
    }

    @Override
    public void onSuccess(
            ExecutionEvent event) {

        System.out.println(
                "[SUCCESS] "
                        + event.getActionType()
        );
    }

    @Override
    public void onRetry(
            ExecutionEvent event) {

        System.out.println(
                "[RETRY] "
                        + event.getActionType()
        );
    }

    @Override
    public void onRecovery(
            ExecutionEvent event) {

        System.out.println(
                "[RECOVERY] "
                        + event.getActionType()
        );
    }

    @Override
    public void onFailure(
            ExecutionEvent event) {

        System.out.println(
                "[FAILED] "
                        + event.getActionType()
        );
    }
}