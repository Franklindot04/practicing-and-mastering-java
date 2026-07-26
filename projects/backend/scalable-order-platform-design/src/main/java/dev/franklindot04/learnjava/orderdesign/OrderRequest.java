package dev.franklindot04.learnjava.orderdesign;

import java.util.List;

public record OrderRequest(String idempotencyKey, String customerId, List<OrderLine> lines) {
    public OrderRequest {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("idempotencyKey is required");
        }
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId is required");
        }
        lines = List.copyOf(lines);
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("at least one line is required");
        }
    }
}
