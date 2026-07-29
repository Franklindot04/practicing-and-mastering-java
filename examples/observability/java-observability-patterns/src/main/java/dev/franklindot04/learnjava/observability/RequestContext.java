package dev.franklindot04.learnjava.observability;

import java.util.Objects;

public record RequestContext(String requestId, String traceId, String operation) {
    public RequestContext {
        requestId = requireText(requestId, "requestId");
        traceId = requireText(traceId, "traceId");
        operation = requireText(operation, "operation");
    }

    private static String requireText(String value, String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " is required");
        }
        return Objects.requireNonNull(value).trim();
    }
}
