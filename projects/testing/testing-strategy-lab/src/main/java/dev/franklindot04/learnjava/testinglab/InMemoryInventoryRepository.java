package dev.franklindot04.learnjava.testinglab;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryInventoryRepository implements InventoryRepository {
    private final Map<String, Integer> stock = new ConcurrentHashMap<>();
    private final Map<String, Reservation> reservationsByRequest = new ConcurrentHashMap<>();

    @Override
    public void putStock(String sku, int quantity) {
        stock.put(sku, quantity);
    }

    @Override
    public int available(String sku) {
        return stock.getOrDefault(sku, 0);
    }

    @Override
    public Optional<Reservation> findByRequestId(String requestId) {
        return Optional.ofNullable(reservationsByRequest.get(requestId));
    }

    @Override
    public synchronized boolean reserve(Reservation reservation) {
        if (reservationsByRequest.containsKey(reservation.requestId())) {
            return true;
        }
        int current = available(reservation.sku());
        if (current < reservation.quantity()) {
            return false;
        }
        stock.put(reservation.sku(), current - reservation.quantity());
        reservationsByRequest.put(reservation.requestId(), reservation);
        return true;
    }
}

