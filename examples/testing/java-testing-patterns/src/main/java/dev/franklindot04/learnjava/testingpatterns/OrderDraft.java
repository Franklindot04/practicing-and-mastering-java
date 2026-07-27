package dev.franklindot04.learnjava.testingpatterns;

import java.util.List;

public record OrderDraft(String customerId, boolean preferredCustomer, List<LineItem> items) {
    public OrderDraft {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customer id is required");
        }
        items = List.copyOf(items);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("order needs at least one item");
        }
    }
}

