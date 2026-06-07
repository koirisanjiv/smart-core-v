package com.qaverse.smart.core.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRegistry<K, V>
        implements Registry<K, V> {

    private final Map<K, V> registry =
            new ConcurrentHashMap<>();

    @Override
    public void register(
            K key,
            V value) {

        registry.put(key, value);
    }

    @Override
    public V get(K key) {

        V value = registry.get(key);

        if (value == null) {
            throw new IllegalStateException(
                    "No registry entry found for: " + key
            );
        }

        return value;
    }

    @Override
    public boolean contains(
            K key) {

        return registry.containsKey(key);
    }

    @Override
    public void clear() {

        registry.clear();
    }

    protected Map<K, V> entries() {

        return registry;
    }
}