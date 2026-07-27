package dev.franklindot04.learnjava.testingpatterns;

import java.time.Instant;
import java.util.List;

public record Order(String id, String customerId, boolean preferredCustomer, List<LineItem> items, Instant createdAt) {
    public Order {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customer id is required");
        }
        items = List.copyOf(items);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("order needs at least one item");
        }
    }
}

