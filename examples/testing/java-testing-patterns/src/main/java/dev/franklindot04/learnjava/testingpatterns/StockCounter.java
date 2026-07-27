package dev.franklindot04.learnjava.testingpatterns;

import java.util.concurrent.atomic.AtomicInteger;

public final class StockCounter {
    private final AtomicInteger available;

    public StockCounter(int initialStock) {
        this.available = new AtomicInteger(initialStock);
    }

    public boolean reserveOne() {
        while (true) {
            int current = available.get();
            if (current <= 0) {
                return false;
            }
            if (available.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }

    public int available() {
        return available.get();
    }
}

