package dev.franklindot04.learnjava.testinglab;

import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.fixedClock;
import static dev.franklindot04.learnjava.testinglab.ReservationTestSupport.request;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class ReservationServiceTest {
    @Test
    void reservesAvailableInventoryAndRecordsNotification() {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 5);
        ReservationTestSupport.RecordingNotifications notifications = new ReservationTestSupport.RecordingNotifications();
        ReservationService service = service(repository, notifications);

        ReservationResult result = service.reserve(request("req-1", 2));

        assertTrue(result.reserved());
        assertEquals(3, repository.available("sku-1"));
        assertEquals(Instant.parse("2026-01-01T00:00:00Z"), result.reservation().reservedAt());
        assertEquals(1, notifications.reservationIds().size());
    }

    @Test
    void rejectsWhenStockIsInsufficient() {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 1);

        ReservationResult result = service(repository, new ReservationTestSupport.RecordingNotifications()).reserve(request("req-1", 2));

        assertFalse(result.reserved());
        assertEquals("insufficient stock", result.reason());
        assertEquals(1, repository.available("sku-1"));
    }

    @Test
    void repeatedRequestReturnsExistingReservationWithoutSecondNotification() {
        InMemoryInventoryRepository repository = new InMemoryInventoryRepository();
        repository.putStock("sku-1", 5);
        ReservationTestSupport.RecordingNotifications notifications = new ReservationTestSupport.RecordingNotifications();
        ReservationService service = service(repository, notifications);

        ReservationResult first = service.reserve(request("req-1", 2));
        ReservationResult second = service.reserve(request("req-1", 2));

        assertEquals(first.reservation().id(), second.reservation().id());
        assertEquals(3, repository.available("sku-1"));
        assertEquals(1, notifications.reservationIds().size());
    }

    private static ReservationService service(InMemoryInventoryRepository repository, ReservationTestSupport.RecordingNotifications notifications) {
        return new ReservationService(repository, notifications, new InventoryPolicy(5), new ReservationTestSupport.SequenceIds(), fixedClock());
    }
}

