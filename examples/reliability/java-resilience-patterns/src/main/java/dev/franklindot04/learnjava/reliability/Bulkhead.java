package dev.franklindot04.learnjava.reliability;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public final class Bulkhead {
    private final Semaphore semaphore;
    private final int maxPermits;

    public Bulkhead(int permits) {
        if (permits < 1) {
            throw new IllegalArgumentException("permits must be positive");
        }
        this.maxPermits = permits;
        this.semaphore = new Semaphore(permits);
    }

    public int active() {
        return maxPermits - semaphore.availablePermits();
    }

    public int available() {
        return semaphore.availablePermits();
    }

    public <T> T execute(Callable<T> work) throws Exception {
        if (!semaphore.tryAcquire()) {
            throw new BulkheadRejectedException("bulkhead is full");
        }
        try {
            return work.call();
        } finally {
            semaphore.release();
        }
    }
}
