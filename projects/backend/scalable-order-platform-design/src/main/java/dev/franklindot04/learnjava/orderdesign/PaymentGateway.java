package dev.franklindot04.learnjava.orderdesign;

public interface PaymentGateway {
    boolean authorize(OrderRequest request);
}
