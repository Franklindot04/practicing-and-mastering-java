package dev.franklindot04.learnjava.systemdesign;

import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.function.Supplier;

public final class Bulkhead {
    private final Semaphore permits;

    public Bulkhead(int maxConcurrentCalls) {
        if (maxConcurrentCalls <= 0) {
            throw new IllegalArgumentException("maxConcurrentCalls must be positive");
        }
        this.permits = new Semaphore(maxConcurrentCalls);
    }

    public <T> Optional<T> tryCall(Supplier<T> action) {
        if (!permits.tryAcquire()) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(action.get());
        } finally {
            permits.release();
        }
    }
}
