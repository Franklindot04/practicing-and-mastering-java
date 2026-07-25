package dev.franklindot04.learnjava.messaging;

public record RetryPolicy(int maxAttempts) {
    public RetryPolicy {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("maxAttempts must be at least 1");
        }
    }
}
