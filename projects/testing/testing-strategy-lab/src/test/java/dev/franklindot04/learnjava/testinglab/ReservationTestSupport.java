package dev.franklindot04.learnjava.testinglab;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

final class ReservationTestSupport {
    static ReservationRequest request(String requestId, int quantity) {
        return new ReservationRequest(requestId, "order-1", "sku-1", quantity);
    }

    static Clock fixedClock() {
        return Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    }

    static final class SequenceIds implements IdGenerator {
        private int next = 1;

        @Override
        public String nextId() {
            return "res-" + next++;
        }
    }

    static final class RecordingNotifications implements NotificationPort {
        private final List<String> reservationIds = new ArrayList<>();

        @Override
        public void reservationCreated(Reservation reservation) {
            reservationIds.add(reservation.id());
        }

        List<String> reservationIds() {
            return List.copyOf(reservationIds);
        }
    }
}

