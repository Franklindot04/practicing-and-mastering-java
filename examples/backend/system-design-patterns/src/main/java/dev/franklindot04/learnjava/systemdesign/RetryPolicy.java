package dev.franklindot04.learnjava.systemdesign;

import java.util.function.Supplier;

public record RetryPolicy(int maxAttempts) {
    public RetryPolicy {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("maxAttempts must be positive");
        }
    }

    public <T> T execute(Supplier<T> action) {
        RuntimeException last = null;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                return action.get();
            } catch (RuntimeException ex) {
                last = ex;
            }
        }
        throw last;
    }
}
