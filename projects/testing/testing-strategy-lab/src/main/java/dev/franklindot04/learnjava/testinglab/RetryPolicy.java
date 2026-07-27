package dev.franklindot04.learnjava.testinglab;

import java.util.function.BooleanSupplier;

public final class RetryPolicy {
    public boolean untilSuccess(int maxAttempts, BooleanSupplier action) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("max attempts must be positive");
        }
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            if (action.getAsBoolean()) {
                return true;
            }
        }
        return false;
    }
}

