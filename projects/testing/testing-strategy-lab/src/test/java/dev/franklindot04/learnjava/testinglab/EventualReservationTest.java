package dev.franklindot04.learnjava.testinglab;

import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.fixedClock;
import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.request;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class EventualReservationTest {
    @Test
    void observesEventuallyCreatedReservationWithBoundedPolling() throws Exception {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 1);
        ReservationService service = new ReservationService(repository, reservation -> { }, new InventoryPolicy(5), new ReservationTestSupport.SequenceIds(), fixedClock());

        Thread worker = new Thread(() -> service.reserve(request("req-1", 1)));
        worker.start();

        boolean observed = waitUntil(Duration.ofMillis(500), () -> repository.findByRequestId("req-1").isPresent());

        worker.join();
        assertTrue(observed);
    }

    private static boolean waitUntil(Duration timeout, Condition condition) throws InterruptedException {
        Instant deadline = Instant.now().plus(timeout);
        while (Instant.now().isBefore(deadline)) {
            if (condition.isMet()) {
                return true;
            }
            Thread.sleep(5);
        }
        return condition.isMet();
    }

    private interface Condition {
        boolean isMet();
    }
}

