package dev.franklindot04.learnjava.systemdesign;

import java.util.function.Supplier;

public final class CircuitBreaker {
    public enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    private final int failureThreshold;
    private State state = State.CLOSED;
    private int consecutiveFailures;

    public CircuitBreaker(int failureThreshold) {
        if (failureThreshold <= 0) {
            throw new IllegalArgumentException("failureThreshold must be positive");
        }
        this.failureThreshold = failureThreshold;
    }

    public <T> T call(Supplier<T> supplier, Supplier<T> fallback) {
        if (state == State.OPEN) {
            return fallback.get();
        }
        try {
            T result = supplier.get();
            consecutiveFailures = 0;
            state = State.CLOSED;
            return result;
        } catch (RuntimeException ex) {
            consecutiveFailures++;
            if (consecutiveFailures >= failureThreshold) {
                state = State.OPEN;
            }
            return fallback.get();
        }
    }

    public void allowProbe() {
        if (state == State.OPEN) {
            state = State.HALF_OPEN;
        }
    }

    public State state() {
        return state;
    }
}
