package com.qaverse.smart.core.retry;

public enum RetryReason {

    MAX_ATTEMPTS_REACHED,

    RECOVERY_ALLOWED,

    RETRY_ALLOWED,

    POLICY_NOT_FOUND
   
}