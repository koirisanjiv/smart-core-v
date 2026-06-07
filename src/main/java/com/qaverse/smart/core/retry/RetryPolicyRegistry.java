package com.qaverse.smart.core.retry;

import java.util.EnumMap;
import java.util.Map;

import com.qaverse.smart.core.failure.FailureType;

public final class RetryPolicyRegistry {

    private static final Map<FailureType, RetryPolicy>
            POLICIES =
            new EnumMap<>(FailureType.class);

    static {

        register(
                new RetryPolicy(
                        FailureType.STALE_ELEMENT,
                        3,
                        true
                )
        );

        register(
                new RetryPolicy(
                        FailureType.CLICK_INTERCEPTED,
                        2,
                        true
                )
        );

        register(
                new RetryPolicy(
                        FailureType.TIMEOUT,
                        2,
                        true
                )
        );

        register(
                new RetryPolicy(
                        FailureType.ELEMENT_NOT_FOUND,
                        2,
                        false
                )
        );
    }

    private RetryPolicyRegistry() {
    }

    public static void register(
            RetryPolicy policy) {

        POLICIES.put(
                policy.getFailureType(),
                policy
        );
    }

    public static RetryPolicy get(
            FailureType type) {

        return POLICIES.getOrDefault(
                type,
                new RetryPolicy(
                        type,
                        1,
                        false
                )
        );
    }
}