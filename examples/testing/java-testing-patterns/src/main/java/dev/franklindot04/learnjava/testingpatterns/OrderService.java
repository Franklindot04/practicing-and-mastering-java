package dev.franklindot04.learnjava.testingpatterns;

import java.time.Clock;
import java.time.Instant;

public final class OrderService {
    private final OrderRepository repository;
    private final NotificationGateway notifications;
    private final IdGenerator ids;
    private final Clock clock;

    public OrderService(OrderRepository repository, NotificationGateway notifications, IdGenerator ids, Clock clock) {
        this.repository = repository;
        this.notifications = notifications;
        this.ids = ids;
        this.clock = clock;
    }

    public Order create(OrderDraft draft) {
        Order order = new Order(ids.nextId(), draft.customerId(), draft.preferredCustomer(), draft.items(), Instant.now(clock));
        repository.save(order);
        notifications.orderCreated(order);
        return order;
    }
}

