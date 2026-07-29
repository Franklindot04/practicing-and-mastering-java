package dev.franklindot04.learnjava.testingpatterns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestingPatternsTest {
    private final PricingService pricing = new PricingService();

    @Test
    void calculatesTotalUsingArrangeActAssert() {
        OrderDraft draft = OrderBuilder.anOrder()
                .withoutDefaultItems()
                .withLine("book", 2, "12.50")
                .buildDraft();

        BigDecimal total = pricing.total(draft, ignored -> BigDecimal.ZERO);

        assertEquals(new BigDecimal("25.00"), total);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 9.99, 9.99",
            "2, 9.99, 19.98",
            "3, 2.50, 7.50"
    })
    void calculatesTotalsForEquivalentValidQuantities(int quantity, String unitPrice, String expected) {
        OrderDraft draft = OrderBuilder.anOrder()
                .withoutDefaultItems()
                .withLine("sku", quantity, unitPrice)
                .buildDraft();

        assertEquals(new BigDecimal(expected), pricing.total(draft, ignored -> BigDecimal.ZERO));
    }

    @Test
    void rejectsBoundaryQuantityAtZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LineItem("book", 0, new BigDecimal("1.00"))
        );

        assertEquals("quantity must be positive", exception.getMessage());
    }

    @Test
    void usesStubDiscountDependency() {
        OrderDraft draft = OrderBuilder.anOrder().forPreferredCustomer().buildDraft();
        DiscountPolicy stubDiscount = ignored -> new BigDecimal("1.50");

        BigDecimal total = pricing.total(draft, stubDiscount);

        assertEquals(new BigDecimal("8.50"), total);
    }

    @Test
    void fakeRepositoryStoresAndFindsOrdersById() {
        FakeOrderRepository repository = new FakeOrderRepository();
        Order order = sampleOrder("order-1");

        repository.save(order);

        assertEquals(Optional.of(order), repository.findById("order-1"));
    }

    @Test
    void recordingNotifierSupportsBehaviorVerification() {
        FakeOrderRepository repository = new FakeOrderRepository();
        RecordingNotifier notifier = new RecordingNotifier();
        OrderService service = new OrderService(repository, notifier, new SequenceIds("order-"), fixedClock());

        Order order = service.create(OrderBuilder.anOrder().buildDraft());

        assertEquals(List.of(order.id()), notifier.recordedOrderIds());
    }

    @Test
    void deterministicClockAndIdGeneratorMakeCreationRepeatable() {
        OrderService service = new OrderService(new FakeOrderRepository(), new RecordingNotifier(), new SequenceIds("order-"), fixedClock());

        Order order = service.create(OrderBuilder.anOrder().buildDraft());

        assertEquals("order-1", order.id());
        assertEquals(Instant.parse("2026-01-01T00:00:00Z"), order.createdAt());
    }

    @Test
    void retryPolicyStopsAfterSuccess() {
        AtomicInteger attempts = new AtomicInteger();
        RetryPolicy retryPolicy = new RetryPolicy(3);

        boolean succeeded = retryPolicy.run(() -> attempts.incrementAndGet() == 2);

        assertTrue(succeeded);
        assertEquals(2, attempts.get());
    }

    @Test
    void retryPolicyReportsFailureAfterMaxAttempts() {
        AtomicInteger attempts = new AtomicInteger();
        RetryPolicy retryPolicy = new RetryPolicy(3);

        boolean succeeded = retryPolicy.run(() -> {
            attempts.incrementAndGet();
            return false;
        });

        assertFalse(succeeded);
        assertEquals(3, attempts.get());
    }

    @Test
    void propertyStyleGeneratedTotalsNeverGoNegative() {
        Random random = new Random(42);
        for (int i = 0; i < 100; i++) {
            int quantity = random.nextInt(1, 20);
            BigDecimal unitPrice = BigDecimal.valueOf(random.nextInt(1, 100));
            OrderDraft draft = OrderBuilder.anOrder()
                    .withoutDefaultItems()
                    .withLine("sku-" + i, quantity, unitPrice.toPlainString())
                    .buildDraft();

            assertTrue(pricing.total(draft, ignored -> BigDecimal.ZERO).signum() >= 0, "case=" + i);
        }
    }

    @Test
    void concurrentReservationsDoNotOversellStock() throws Exception {
        StockCounter stock = new StockCounter(10);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(20);
        AtomicInteger successes = new AtomicInteger();
        var executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 20; i++) {
                executor.submit(() -> {
                    await(start);
                    if (stock.reserveOne()) {
                        successes.incrementAndGet();
                    }
                    done.countDown();
                });
            }
            start.countDown();

            assertTrue(done.await(1, TimeUnit.SECONDS));
            assertEquals(10, successes.get());
            assertEquals(0, stock.available());
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void eventualResultUsesBoundedPolling() throws Exception {
        FakeOrderRepository repository = new FakeOrderRepository();
        Thread worker = new Thread(() -> repository.save(sampleOrder("eventual-1")));
        worker.start();

        boolean observed = waitUntil(Duration.ofMillis(500), () -> repository.findById("eventual-1").isPresent());

        worker.join();
        assertTrue(observed);
    }

    private static Order sampleOrder(String id) {
        return new Order(id, "customer-1", false, List.of(new LineItem("book", 1, new BigDecimal("10.00"))), Instant.parse("2026-01-01T00:00:00Z"));
    }

    private static Clock fixedClock() {
        return Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(exception);
        }
    }

    private static boolean waitUntil(Duration timeout, Condition condition) throws InterruptedException {
        Instant deadline = Instant.now().plus(timeout);
        while (Instant.now().isBefore(deadline)) {
            if (condition.isMet()) {
                return true;
            }
            Thread.sleep(5);
        }
        return condition.isMet();
    }

    private interface Condition {
        boolean isMet();
    }

    private static final class FakeOrderRepository implements OrderRepository {
        private final Map<String, Order> orders = new java.util.concurrent.ConcurrentHashMap<>();

        @Override
        public void save(Order order) {
            orders.put(order.id(), order);
        }

        @Override
        public Optional<Order> findById(String id) {
            return Optional.ofNullable(orders.get(id));
        }
    }

    private static final class RecordingNotifier implements NotificationGateway {
        private final List<String> orderIds = new ArrayList<>();

        @Override
        public void orderCreated(Order order) {
            orderIds.add(order.id());
        }

        List<String> recordedOrderIds() {
            return List.copyOf(orderIds);
        }
    }

    private static final class SequenceIds implements IdGenerator {
        private final String prefix;
        private int next = 1;

        private SequenceIds(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public String nextId() {
            return prefix + next++;
        }
    }
}

