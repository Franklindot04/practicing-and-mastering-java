package dev.franklindot04.learnjava.reliability;

import java.time.Duration;
import java.util.Objects;
import java.util.function.LongUnaryOperator;

public final class ExponentialBackoff {
    private final Duration initialDelay; private final Duration maxDelay; private final LongUnaryOperator jitter;
    public ExponentialBackoff(Duration initialDelay, Duration maxDelay, LongUnaryOperator jitter) {
        if (initialDelay.isNegative() || initialDelay.isZero()) throw new IllegalArgumentException("initialDelay must be positive");
        if (maxDelay.compareTo(initialDelay) < 0) throw new IllegalArgumentException("maxDelay must be at least initialDelay");
        this.initialDelay = initialDelay; this.maxDelay = maxDelay; this.jitter = Objects.requireNonNull(jitter);
    }
    public Duration delayForAttempt(int attemptNumber) {
        if (attemptNumber < 2) return Duration.ZERO;
        long base = initialDelay.toMillis();
        long raw = base * (1L << Math.min(30, attemptNumber - 2));
        long capped = Math.min(raw, maxDelay.toMillis());
        long jittered = Math.max(0, Math.min(maxDelay.toMillis(), jitter.applyAsLong(capped)));
        return Duration.ofMillis(jittered);
    }
}
