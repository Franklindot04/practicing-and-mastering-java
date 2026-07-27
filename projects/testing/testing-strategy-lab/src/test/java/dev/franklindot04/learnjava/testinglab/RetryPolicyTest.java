package dev.franklindot04.learnjava.testinglab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class RetryPolicyTest {
    @Test
    void retriesUntilOperationSucceeds() {
        AtomicInteger attempts = new AtomicInteger();

        boolean result = new RetryPolicy().untilSuccess(4, () -> attempts.incrementAndGet() == 3);

        assertTrue(result);
        assertEquals(3, attempts.get());
    }
}

