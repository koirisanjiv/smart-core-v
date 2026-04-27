package com.qaverse.smart.FieldActionHandler;

import java.util.HashMap;
import java.util.Map;

import com.qaverse.smart.Locator.FieldType;

public class FieldActionRegistry {

	 private static boolean initialized = false;

	    private static void ensureInit() {
	        if (!initialized) {
	            HandlerLoader.init();
	            initialized = true;
	        }
	    }


	private static final Map<FieldType, FieldActionHandler> handlers = new HashMap<>();

	public static void register(FieldActionHandler handler) {
		handlers.put(handler.getType(), handler);
	}

	public static FieldActionHandler getHandler(FieldType type) {
		 ensureInit();

		FieldActionHandler handler = handlers.get(type);

		if (handler == null) {
			throw new RuntimeException("No handler for type: " + type);
		}

		return handler;
	}
}