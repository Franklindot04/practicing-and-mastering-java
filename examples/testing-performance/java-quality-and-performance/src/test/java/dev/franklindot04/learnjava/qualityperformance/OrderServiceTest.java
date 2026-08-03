package dev.franklindot04.learnjava.qualityperformance;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    private final Clock fixed = Clock.fixed(Instant.parse("2026-08-03T10:15:30Z"), ZoneOffset.UTC);
    @ParameterizedTest @ValueSource(ints = {1, 2, 20})
    void acceptsBoundaryQuantities(int quantity) {
        var inventory = new OrderService.InMemoryInventory(); inventory.put("BOOK", 25);
        var sink = new OrderService.RecordingSink(); AtomicInteger ids = new AtomicInteger();
        var service = new OrderService(fixed, () -> "id-" + ids.incrementAndGet(), inventory, sink, 29);
        var receipt = service.reserve(" book ", quantity);
        assertAll(() -> assertTrue(receipt.accepted()), () -> assertEquals("BOOK", receipt.sku()), () -> assertEquals(fixed.instant(), receipt.createdAt()), () -> assertEquals(25 - quantity, inventory.available("BOOK")));
    }
    @Nested class Validation {
        @Test void rejectsInvalidQuantity() {
            var service = new OrderService(fixed, () -> "id", new OrderService.InMemoryInventory(), event -> {}, 29);
            assertThrows(IllegalArgumentException.class, () -> service.reserve("A", 0));
        }
    }
    @Test void fakeAndSpyShowStateBasedAlternativeToBrittleInteractionChecks() {
        var inventory = new OrderService.InMemoryInventory(); inventory.put("A", 1);
        var sink = new OrderService.RecordingSink();
        var service = new OrderService(fixed, () -> "fixed", inventory, sink, 29);
        service.reserve("A", 1);
        assertEquals(0, inventory.available("A"));
        assertEquals(1, sink.events().size());
        assertTrue(sink.events().get(0).startsWith("reservation.accepted:A"));
    }
}
