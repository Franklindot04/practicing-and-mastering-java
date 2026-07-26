package dev.franklindot04.learnjava.orderdesign;

public interface InventoryGateway {
    boolean reserve(OrderRequest request);
}
