package dev.franklindot04.learnjava.testinglab;

public record ReservationResult(boolean reserved, String reason, Reservation reservation) {
    static ReservationResult reserved(Reservation reservation) {
        return new ReservationResult(true, "reserved", reservation);
    }

    static ReservationResult rejected(String reason) {
        return new ReservationResult(false, reason, null);
    }
}

