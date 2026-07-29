package dev.franklindot04.learnjava.reliability;

import java.time.Duration;

public record RetryPolicy(int maxAttempts, Duration initialDelay, Duration maxDelay, Duration maxElapsed) {
    public RetryPolicy {
        if (maxAttempts < 1) throw new IllegalArgumentException("maxAttempts must be positive");
        if (initialDelay.isNegative() || initialDelay.isZero()) throw new IllegalArgumentException("initialDelay must be positive");
        if (maxDelay.compareTo(initialDelay) < 0) throw new IllegalArgumentException("maxDelay must be at least initialDelay");
        if (maxElapsed.isNegative() || maxElapsed.isZero()) throw new IllegalArgumentException("maxElapsed must be positive");
    }
}
