package dev.franklindot04.learnjava.orderdesign;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderDesignTest {
    @Test
    void createsOrderWhenInventoryAndPaymentSucceed() {
        List<Order> notifications = new ArrayList<>();
        OrderService service = new OrderService(request -> true, request -> true, notifications::add, new IdempotencyStore());

        Order order = service.submit(request("key-1"));

        assertEquals(OrderStatus.CREATED, order.status());
        assertTrue(service.findById(order.orderId()).isPresent());
        assertEquals(List.of(order), notifications);
    }

    @Test
    void idempotencyKeyReturnsOriginalOrderForDuplicateSubmission() {
        IdempotencyStore store = new IdempotencyStore();
        OrderService service = new OrderService(request -> true, request -> true, order -> {
        }, store);

        Order first = service.submit(request("same-key"));
        Order second = service.submit(request("same-key"));

        assertEquals(first, second);
        assertEquals(1, store.size());
    }

    @Test
    void rejectsOrderWhenInventoryFailsBeforePayment() {
        OrderService service = new OrderService(request -> false, request -> {
            throw new IllegalStateException("payment should not be called");
        }, order -> {
        }, new IdempotencyStore());

        Order order = service.submit(request("inventory-failure"));

        assertEquals(OrderStatus.REJECTED_INVENTORY, order.status());
        assertEquals("inventory unavailable", order.reason());
    }

    @Test
    void rejectsOrderWhenPaymentFails() {
        OrderService service = new OrderService(request -> true, request -> false, order -> {
        }, new IdempotencyStore());

        Order order = service.submit(request("payment-failure"));

        assertEquals(OrderStatus.REJECTED_PAYMENT, order.status());
        assertEquals("payment authorization failed", order.reason());
    }

    @Test
    void capacityEstimateComputesPeakAndReadHeavyShape() {
        CapacityEstimate estimate = new CapacityEstimate(20, 200, 5);

        assertEquals(100, estimate.peakWritesPerSecond());
        assertEquals(1000, estimate.peakReadsPerSecond());
        assertTrue(estimate.readHeavy());
    }

    private OrderRequest request(String key) {
        return new OrderRequest(key, "customer-1", List.of(new OrderLine("sku-1", 2)));
    }
}
