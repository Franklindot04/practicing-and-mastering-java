package dev.franklindot04.learnjava.orderdesign;

public interface NotificationGateway {
    void orderStatusChanged(Order order);
}
