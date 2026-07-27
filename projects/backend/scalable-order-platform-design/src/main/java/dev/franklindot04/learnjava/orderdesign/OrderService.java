package dev.franklindot04.learnjava.orderdesign;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public final class OrderService {
    private final InventoryGateway inventory;
    private final PaymentGateway payment;
    private final NotificationGateway notifications;
    private final IdempotencyStore idempotencyStore;
    private final Map<String, Order> orders = new HashMap<>();

    public OrderService(
            InventoryGateway inventory,
            PaymentGateway payment,
            NotificationGateway notifications,
            IdempotencyStore idempotencyStore) {
        this.inventory = inventory;
        this.payment = payment;
        this.notifications = notifications;
        this.idempotencyStore = idempotencyStore;
    }

    public Order submit(OrderRequest request) {
        return idempotencyStore.existingOrCreate(request.idempotencyKey(), () -> createOrder(request));
    }

    public Optional<Order> findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    private Order createOrder(OrderRequest request) {
        Order order;
        if (!inventory.reserve(request)) {
            order = rejected(request, OrderStatus.REJECTED_INVENTORY, "inventory unavailable");
        } else if (!payment.authorize(request)) {
            order = rejected(request, OrderStatus.REJECTED_PAYMENT, "payment authorization failed");
        } else {
            order = new Order(newOrderId(), request.customerId(), request.lines(), OrderStatus.CREATED, "created");
        }
        orders.put(order.orderId(), order);
        notifications.orderStatusChanged(order);
        return order;
    }

    private Order rejected(OrderRequest request, OrderStatus status, String reason) {
        return new Order(newOrderId(), request.customerId(), request.lines(), status, reason);
    }

    private String newOrderId() {
        return "order-" + UUID.randomUUID();
    }
}
