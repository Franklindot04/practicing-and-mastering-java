package dev.franklindot04.learnjava.performance;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class BoundedCache<K, V> {
    private final Map<K, V> entries;

    public BoundedCache(int maxEntries) {
        if (maxEntries <= 0) {
            throw new IllegalArgumentException("maxEntries must be positive");
        }
        this.entries = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }
        };
    }

    public synchronized void put(K key, V value) {
        entries.put(key, value);
    }

    public synchronized Optional<V> get(K key) {
        return Optional.ofNullable(entries.get(key));
    }

    public synchronized int size() {
        return entries.size();
    }
}
