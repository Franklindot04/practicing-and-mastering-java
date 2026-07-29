package dev.franklindot04.learnjava.testinglab;

import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.fixedClock;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class InventoryRepositoryContractTest {
    @Test
    void repositoryContractDoesNotCreateReservationsWhenStockIsMissing() {
        InventoryRepository repository = new InMemoryInventoryRepository();
        Reservation reservation = new Reservation("res-1", "req-1", "order-1", "sku-1", 1, Instant.now(fixedClock()));

        assertFalse(repository.reserve(reservation));
        assertTrue(repository.findByRequestId("req-1").isEmpty());
    }
}

