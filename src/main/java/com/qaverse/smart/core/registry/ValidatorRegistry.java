package com.qaverse.smart.core.registry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.qaverse.smart.core.contract.Validator;

public final class ValidatorRegistry {

	private static final List<Validator> VALIDATORS = new CopyOnWriteArrayList<>();

	private ValidatorRegistry() {
	}

	public static void register(Validator validator) {

		if (validator == null) {
			return;
		}

		boolean alreadyRegistered = VALIDATORS.stream()
				.anyMatch(existing -> existing.getClass().equals(validator.getClass()));

		if (!alreadyRegistered) {

			VALIDATORS.add(validator);
		}
	}

	public static List<Validator> getAll() {

		return List.copyOf(VALIDATORS);
	}

	public static void clear() {

		VALIDATORS.clear();
	}
}