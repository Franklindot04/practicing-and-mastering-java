package dev.franklindot04.learnjava.systemdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class CacheAside<K, V> {
    private final Map<K, V> cache = new HashMap<>();
    private final Function<K, Optional<V>> loader;

    public CacheAside(Function<K, Optional<V>> loader) {
        this.loader = loader;
    }

    public Optional<V> get(K key) {
        if (cache.containsKey(key)) {
            return Optional.ofNullable(cache.get(key));
        }
        Optional<V> loaded = loader.apply(key);
        loaded.ifPresent(value -> cache.put(key, value));
        return loaded;
    }

    public void invalidate(K key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }
}
