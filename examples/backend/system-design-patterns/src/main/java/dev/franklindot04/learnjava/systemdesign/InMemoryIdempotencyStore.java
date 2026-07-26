package dev.franklindot04.learnjava.systemdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public final class InMemoryIdempotencyStore {
    private final Map<String, String> results = new HashMap<>();

    public String deduplicate(String key, Supplier<String> action) {
        return results.computeIfAbsent(key, ignored -> action.get());
    }

    public Optional<String> resultFor(String key) {
        return Optional.ofNullable(results.get(key));
    }
}
