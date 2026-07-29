package dev.franklindot04.learnjava.perflab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class LruCache<K, V> {
    private final Map<K, V> entries;

    public LruCache(int capacity) {
        this.entries = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public void put(K key, V value) {
        entries.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(entries.get(key));
    }

    public int size() {
        return entries.size();
    }
}
