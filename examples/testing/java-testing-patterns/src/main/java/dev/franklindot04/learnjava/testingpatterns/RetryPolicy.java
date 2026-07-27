package dev.franklindot04.learnjava.testingpatterns;

import java.util.function.BooleanSupplier;

public final class RetryPolicy {
    private final int maxAttempts;

    public RetryPolicy(int maxAttempts) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("max attempts must be positive");
        }
        this.maxAttempts = maxAttempts;
    }

    public boolean run(BooleanSupplier action) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            if (action.getAsBoolean()) {
                return true;
            }
        }
        return false;
    }
}

