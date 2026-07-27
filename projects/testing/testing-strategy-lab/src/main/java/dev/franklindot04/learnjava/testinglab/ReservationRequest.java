package dev.franklindot04.learnjava.testinglab;

public record ReservationRequest(String requestId, String orderId, String sku, int quantity) {
    public ReservationRequest {
        if (requestId == null || requestId.isBlank()) {
            throw new IllegalArgumentException("request id is required");
        }
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("order id is required");
        }
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("sku is required");
        }
    }
}

