package dev.franklindot04.learnjava.observability;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public record StructuredEvent(
        Instant timestamp,
        String eventName,
        Severity severity,
        String operation,
        String requestId,
        Outcome outcome,
        long durationNanos,
        Map<String, String> attributes) {

    public StructuredEvent {
        timestamp = Objects.requireNonNull(timestamp);
        eventName = requireText(eventName, "eventName");
        severity = Objects.requireNonNull(severity);
        operation = requireText(operation, "operation");
        requestId = requireText(requestId, "requestId");
        outcome = Objects.requireNonNull(outcome);
        if (durationNanos < 0) {
            throw new IllegalArgumentException("durationNanos cannot be negative");
        }
        attributes = Map.copyOf(Redactor.redact(attributes == null ? Map.of() : attributes));
    }

    public String format() {
        StringBuilder builder = new StringBuilder()
                .append("timestamp=").append(timestamp)
                .append(" event=").append(eventName)
                .append(" severity=").append(severity)
                .append(" operation=").append(operation)
                .append(" requestId=").append(requestId)
                .append(" outcome=").append(outcome)
                .append(" durationNanos=").append(durationNanos);
        new TreeMap<>(attributes).forEach((key, value) -> builder.append(' ').append(key).append('=').append(value));
        return builder.toString();
    }

    private static String requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " is required");
        }
        return value.trim();
    }
}
