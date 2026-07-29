package dev.franklindot04.learnjava.observability;

public enum HealthStatus {
    UP, DEGRADED, DOWN;

    static HealthStatus worst(HealthStatus left, HealthStatus right) {
        return left.ordinal() >= right.ordinal() ? left : right;
    }
}
