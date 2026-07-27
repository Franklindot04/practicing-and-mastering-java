package dev.franklindot04.learnjava.orderdesign;

import java.util.List;

public record Order(String orderId, String customerId, List<OrderLine> lines, OrderStatus status, String reason) {
    public Order {
        lines = List.copyOf(lines);
    }
}
