package dev.franklindot04.learnjava.testinglab;

import java.time.Instant;

public record Reservation(String id, String requestId, String orderId, String sku, int quantity, Instant reservedAt) {
}

