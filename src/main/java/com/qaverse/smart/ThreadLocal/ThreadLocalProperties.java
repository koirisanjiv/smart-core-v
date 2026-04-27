package com.qaverse.smart.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalProperties {
	private static ThreadLocal<Map<String, String>> threadProps = ThreadLocal.withInitial(HashMap::new);

	public static void setProperty(String key, String value) {
		threadProps.get().put(key, value);
	}

	public static String getProperty(String key) {
		return threadProps.get().get(key);
	}

	public static void removeProperty() {
		threadProps.remove();
	}

	// Case-insensitive getter
	public static String getSystemPropertyIgnoreCase(String key) {
		Map<String, String> props = threadProps.get();
		for (String k : props.keySet()) {
			if (k.equalsIgnoreCase(key)) {
				return props.get(k);
			}
		}
		return null;
	}

	// Case-insensitive getter with default value
	public static String getSystemPropertyIgnoreCase(String key, String defaultValue) {
		Map<String, String> props = threadProps.get();
		for (String k : props.keySet()) {
			if (k.equalsIgnoreCase(key)) {
				return props.get(k);
			}
		}
		return defaultValue;
	}
}
