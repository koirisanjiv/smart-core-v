package com.qaverse.smart.ActionMethod;

import java.util.concurrent.ConcurrentHashMap;


public class ActionFactory {
	private static final ThreadLocal<ConcurrentHashMap<Class<? extends MyActions>, MyActions>> threadLocalActions = ThreadLocal
			.withInitial(ConcurrentHashMap::new);

	@SuppressWarnings("unchecked")
	public static <T extends MyActions> T getAction(Class<T> actionClass) {
		return (T) threadLocalActions.get().computeIfAbsent(actionClass, clazz -> {
			try {
				return clazz.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException("Failed to create instance of " + clazz.getSimpleName(), e);
			}
		});
	}

	public static void clearAndRemove() {
		threadLocalActions.get().clear();
		 threadLocalActions.remove();
	}

}
