package dev.franklindot04.learnjava.orderdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class IdempotencyStore {
    private final Map<String, Order> results = new HashMap<>();

    public Order existingOrCreate(String key, Supplier<Order> creator) {
        return results.computeIfAbsent(key, ignored -> creator.get());
    }

    public int size() {
        return results.size();
    }
}
