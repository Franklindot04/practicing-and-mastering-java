package dev.franklindot04.learnjava.qualityperformance;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class OrderService {
    private final Clock clock;
    private final IdGenerator ids;
    private final Inventory inventory;
    private final EventSink events;
    private final Random random;

    public OrderService(Clock clock, IdGenerator ids, Inventory inventory, EventSink events, long seed) {
        this.clock = Objects.requireNonNull(clock);
        this.ids = Objects.requireNonNull(ids);
        this.inventory = Objects.requireNonNull(inventory);
        this.events = Objects.requireNonNull(events);
        this.random = new Random(seed);
    }

    public Receipt reserve(String sku, int quantity) {
        if (quantity < 1 || quantity > 20) {
            throw new IllegalArgumentException("quantity must be between 1 and 20");
        }
        String normalized = sku == null ? "" : sku.trim().toUpperCase();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("sku is required");
        }
        boolean reserved = inventory.reserve(normalized, quantity);
        if (!reserved) {
            events.publish("reservation.rejected:" + normalized);
            return new Receipt(ids.nextId(), normalized, 0, Instant.now(clock), false);
        }
        int auditSample = random.nextInt(100);
        events.publish("reservation.accepted:" + normalized + ":sample=" + auditSample);
        return new Receipt(ids.nextId(), normalized, quantity, Instant.now(clock), true);
    }

    public interface IdGenerator { String nextId(); }
    public interface Inventory { boolean reserve(String sku, int quantity); int available(String sku); }
    public interface EventSink { void publish(String event); }
    public record Receipt(String id, String sku, int quantity, Instant createdAt, boolean accepted) {}
    public static final class InMemoryInventory implements Inventory {
        private final java.util.Map<String,Integer> stock = new java.util.HashMap<>();
        public void put(String sku, int quantity) { stock.put(sku, quantity); }
        public boolean reserve(String sku, int quantity) { int available=available(sku); if(available<quantity) return false; stock.put(sku, available-quantity); return true; }
        public int available(String sku) { return stock.getOrDefault(sku,0); }
    }
    public static final class RecordingSink implements EventSink {
        private final List<String> events = new ArrayList<>();
        public void publish(String event) { events.add(event); }
        public List<String> events() { return List.copyOf(events); }
    }
}
