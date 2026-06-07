package com.qaverse.smart.core.retry;

import java.util.EnumMap;
import java.util.Map;

import com.qaverse.smart.core.failure.FailureType;

public final class RetryPolicyRegistry {

	private static final Map<FailureType, RetryPolicy> POLICIES = new EnumMap<>(FailureType.class);

	static {

		register(new RetryPolicy(FailureType.STALE_ELEMENT, RetryConstants.STALE_MAX_ATTEMPTS, true,
				RetryStrategyType.ADAPTIVE));

		register(new RetryPolicy(FailureType.CLICK_INTERCEPTED, RetryConstants.CLICK_INTERCEPTED_MAX_ATTEMPTS, true,
				RetryStrategyType.EXPONENTIAL_BACKOFF));

		register(new RetryPolicy(FailureType.TIMEOUT, RetryConstants.TIMEOUT_MAX_ATTEMPTS, true,
				RetryStrategyType.FIXED_DELAY));

		register(new RetryPolicy(FailureType.ELEMENT_NOT_FOUND, RetryConstants.ELEMENT_NOT_FOUND_MAX_ATTEMPTS, false,
				RetryStrategyType.NONE));
	}

	private RetryPolicyRegistry() {
	}

	public static void register(RetryPolicy policy) {

		POLICIES.put(policy.getFailureType(), policy);
	}

	public static RetryPolicy get(FailureType type) {

		return POLICIES.getOrDefault(type,
				new RetryPolicy(type, RetryConstants.DEFAULT_MAX_ATTEMPTS, false, RetryStrategyType.NONE));
	}
	
	public static RetryPolicy getDefaultPolicy() {

	    return new RetryPolicy(
	            FailureType.UNKNOWN,
	            RetryConstants.DEFAULT_MAX_ATTEMPTS,
	            false,
	            RetryStrategyType.NONE
	    );
	}
}