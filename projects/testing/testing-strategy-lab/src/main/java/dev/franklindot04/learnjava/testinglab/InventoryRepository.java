package dev.franklindot04.learnjava.testinglab;

import java.util.Optional;

public interface InventoryRepository {
    void putStock(String sku, int quantity);

    int available(String sku);

    Optional<Reservation> findByRequestId(String requestId);

    boolean reserve(Reservation reservation);
}

