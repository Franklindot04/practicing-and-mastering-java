package dev.franklindot04.learnjava.testinglab;

import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.fixedClock;
import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.request;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class ReservationConcurrencyTest {
    @Test
    void concurrentRequestsDoNotReserveMoreThanAvailableStock() throws Exception {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 3);
        ReservationService service = new ReservationService(repository, reservation -> { }, new InventoryPolicy(5), new ReservationTestSupport.SequenceIds(), fixedClock());
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(6);
        AtomicInteger successes = new AtomicInteger();
        var executor = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 6; i++) {
                int requestNumber = i;
                executor.submit(() -> {
                    await(start);
                    if (service.reserve(request("req-" + requestNumber, 1)).reserved()) {
                        successes.incrementAndGet();
                    }
                    done.countDown();
                });
            }
            start.countDown();

            assertTrue(done.await(1, TimeUnit.SECONDS));
            assertEquals(3, successes.get());
            assertEquals(0, repository.available("sku-1"));
        } finally {
            executor.shutdownNow();
        }
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(exception);
        }
    }
}

