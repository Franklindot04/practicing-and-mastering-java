package dev.franklindot04.learnjava.perflab;

import java.math.BigDecimal;
import java.time.Instant;

public record Event(String id, String customerId, String type, BigDecimal amount, Instant occurredAt) {
}
