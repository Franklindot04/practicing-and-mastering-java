package dev.franklindot04.learnjava.reliability;

public record CircuitBreakerSnapshot(CircuitBreakerState state, int failures, int activeProbes) {}
