package dev.franklindot04.learnjava.testinglab;

import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.fixedClock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class InMemoryInventoryRepositoryTest {
    @Test
    void storesStockAndReservationState() {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 4);
        Reservation reservation = new Reservation("res-1", "req-1", "order-1", "sku-1", 2, Instant.now(fixedClock()));

        assertTrue(repository.reserve(reservation));

        assertEquals(2, repository.available("sku-1"));
        assertEquals(reservation, repository.findByRequestId("req-1").orElseThrow());
    }
}

