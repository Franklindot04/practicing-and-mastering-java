package dev.franklindot04.learnjava.testinglab;

import java.time.Clock;
import java.time.Instant;

public final class ReservationService {
    private final InventoryRepository repository;
    private final NotificationPort notifications;
    private final InventoryPolicy policy;
    private final IdGenerator ids;
    private final Clock clock;

    public ReservationService(InventoryRepository repository, NotificationPort notifications, InventoryPolicy policy, IdGenerator ids, Clock clock) {
        this.repository = repository;
        this.notifications = notifications;
        this.policy = policy;
        this.ids = ids;
        this.clock = clock;
    }

    public ReservationResult reserve(ReservationRequest request) {
        if (!policy.accepts(request.quantity())) {
            return ReservationResult.rejected(policy.rejectionReason(request.quantity()));
        }
        return repository.findByRequestId(request.requestId())
                .map(ReservationResult::reserved)
                .orElseGet(() -> reserveNew(request));
    }

    private ReservationResult reserveNew(ReservationRequest request) {
        Reservation reservation = new Reservation(ids.nextId(), request.requestId(), request.orderId(), request.sku(), request.quantity(), Instant.now(clock));
        if (!repository.reserve(reservation)) {
            return ReservationResult.rejected("insufficient stock");
        }
        notifications.reservationCreated(reservation);
        return ReservationResult.reserved(reservation);
    }
}

