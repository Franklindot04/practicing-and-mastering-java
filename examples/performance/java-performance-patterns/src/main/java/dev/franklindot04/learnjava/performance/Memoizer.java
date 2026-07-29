package dev.franklindot04.learnjava.performance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public final class Memoizer<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Function<K, V> loader;

    public Memoizer(Function<K, V> loader) {
        this.loader = loader;
    }

    public V get(K key) {
        return cache.computeIfAbsent(key, loader);
    }

    public int cachedValues() {
        return cache.size();
    }
}
