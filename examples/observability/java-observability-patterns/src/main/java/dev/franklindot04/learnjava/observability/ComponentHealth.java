package dev.franklindot04.learnjava.observability;

public record ComponentHealth(String component, HealthStatus status, String detail) {
}
