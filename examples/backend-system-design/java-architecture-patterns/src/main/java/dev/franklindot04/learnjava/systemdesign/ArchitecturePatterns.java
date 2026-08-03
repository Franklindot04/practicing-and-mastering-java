package dev.franklindot04.learnjava.systemdesign;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class ArchitecturePatterns {
    private ArchitecturePatterns() {
    }

    public interface CustomerPort {
        CustomerProfile findCustomer(String customerId);
    }

    public interface OrderPort {
        OrderReceipt placeOrder(String customerId, int itemCount);
    }

    public record DomainEvent(String type, String aggregateId) {
    }

    public record CustomerProfile(String customerId, String displayName) {
    }

    public record OrderReceipt(String orderId, String customerId, int itemCount) {
    }

    public static final class ModularMonolith {
        private final List<DomainEvent> events = new ArrayList<>();
        private final CustomerPort customerPort = id -> new CustomerProfile(id, "customer-" + id);
        private final OrderPort orderPort = (customerId, itemCount) -> {
            var receipt = new OrderReceipt("order-" + customerId + "-" + itemCount, customerId, itemCount);
            events.add(new DomainEvent("OrderPlaced", receipt.orderId()));
            return receipt;
        };

        public CustomerPort customers() {
            return customerPort;
        }

        public OrderPort orders() {
            return orderPort;
        }

        public List<DomainEvent> events() {
            return List.copyOf(events);
        }
    }

    public record DownstreamResponse(String source, boolean successful, String body, Duration latency) {
    }

    public record AggregatedResponse(String correlationId, Map<String, String> sections, List<String> degraded) {
    }

    public static final class DownstreamClient {
        private final String name;
        private final Duration latency;
        private final boolean fails;
        private int calls;
        private boolean circuitOpen;

        public DownstreamClient(String name, Duration latency, boolean fails) {
            this.name = name;
            this.latency = latency;
            this.fails = fails;
        }

        DownstreamResponse call(String correlationId, Duration budget) {
            calls++;
            if (circuitOpen || fails || latency.compareTo(budget) > 0 || correlationId.isBlank()) {
                circuitOpen = true;
                return new DownstreamResponse(name, false, "fallback-" + name, latency);
            }
            return new DownstreamResponse(name, true, "value-" + name + "-" + correlationId, latency);
        }

        public int calls() {
            return calls;
        }

        public boolean circuitOpen() {
            return circuitOpen;
        }
    }

    public static final class ApiAggregator {
        private final List<DownstreamClient> clients;

        public ApiAggregator(List<DownstreamClient> clients) {
            this.clients = List.copyOf(clients);
        }

        public AggregatedResponse aggregate(String correlationId, Duration requestBudget) {
            Duration perDependencyBudget = requestBudget.dividedBy(Math.max(1, clients.size()));
            Map<String, String> sections = new LinkedHashMap<>();
            List<String> degraded = new ArrayList<>();
            for (DownstreamClient client : clients) {
                DownstreamResponse response = client.call(correlationId, perDependencyBudget);
                sections.put(response.source(), response.body());
                if (!response.successful()) {
                    degraded.add(response.source());
                }
            }
            return new AggregatedResponse(correlationId, sections, degraded);
        }
    }

    public record CacheMetrics(int hits, int misses, int backendLoads, int staleResponses, int negativeHits) {
    }

    public static final class CacheAside<K, V> {
        private final Map<K, Entry<V>> values = new HashMap<>();
        private final Set<K> loading = new HashSet<>();
        private int hits;
        private int misses;
        private int backendLoads;
        private int staleResponses;
        private int negativeHits;

        public Optional<V> get(K key, long now, Duration ttl, Supplier<Optional<V>> loader) {
            Entry<V> entry = values.get(key);
            if (entry != null && !entry.expired(now)) {
                hits++;
                if (entry.negative()) {
                    negativeHits++;
                }
                return Optional.ofNullable(entry.value());
            }
            misses++;
            if (loading.contains(key)) {
                staleResponses++;
                return entry == null ? Optional.empty() : Optional.ofNullable(entry.value());
            }
            loading.add(key);
            backendLoads++;
            Optional<V> loaded = loader.get();
            values.put(key, new Entry<>(loaded.orElse(null), now + ttl.toMillis(), loaded.isEmpty()));
            loading.remove(key);
            return loaded;
        }

        public Optional<V> staleWhileLoading(K key) {
            loading.add(key);
            Entry<V> entry = values.get(key);
            staleResponses++;
            return entry == null ? Optional.empty() : Optional.ofNullable(entry.value());
        }

        public void put(K key, V value, long expiresAt) {
            values.put(key, new Entry<>(value, expiresAt, false));
        }

        public void invalidate(K key) {
            values.remove(key);
        }

        public CacheMetrics metrics() {
            return new CacheMetrics(hits, misses, backendLoads, staleResponses, negativeHits);
        }

        private record Entry<V>(V value, long expiresAt, boolean negative) {
            boolean expired(long now) {
                return now >= expiresAt;
            }
        }
    }

    public static final class PartitionedRepository {
        private final int partitions;
        private final Map<Integer, Integer> load = new HashMap<>();

        public PartitionedRepository(int partitions) {
            this.partitions = partitions;
        }

        public int route(String key) {
            int partition = Math.floorMod(key.hashCode(), partitions);
            load.merge(partition, 1, Integer::sum);
            return partition;
        }

        public List<Integer> hotPartitions(int threshold) {
            return load.entrySet().stream()
                    .filter(entry -> entry.getValue() >= threshold)
                    .map(Map.Entry::getKey)
                    .sorted()
                    .toList();
        }

        public int crossPartitionQueryCost() {
            return partitions;
        }

        public Map<Integer, List<String>> rebalancePlan(int newPartitionCount, List<String> keys) {
            Map<Integer, List<String>> plan = new LinkedHashMap<>();
            for (String key : keys) {
                int target = Math.floorMod(key.hashCode(), newPartitionCount);
                plan.computeIfAbsent(target, ignored -> new ArrayList<>()).add(key);
            }
            return plan;
        }
    }

    public enum SagaStatus {
        STARTED, INVENTORY_RESERVED, COMPLETED, COMPENSATED, NEEDS_RECONCILIATION
    }

    public static final class CheckoutSaga {
        private final Set<String> idempotencyKeys = ConcurrentHashMap.newKeySet();
        private final List<String> audit = new ArrayList<>();
        private int availableInventory;

        public CheckoutSaga(int availableInventory) {
            this.availableInventory = availableInventory;
        }

        public SagaStatus checkout(String key, boolean paymentSucceeds, boolean compensationSucceeds) {
            if (!idempotencyKeys.add(key)) {
                audit.add("duplicate:" + key);
                return SagaStatus.COMPLETED;
            }
            audit.add("start:" + key);
            if (availableInventory <= 0) {
                audit.add("no-inventory:" + key);
                return SagaStatus.NEEDS_RECONCILIATION;
            }
            availableInventory--;
            audit.add("reserved:" + key);
            if (paymentSucceeds) {
                audit.add("completed:" + key);
                return SagaStatus.COMPLETED;
            }
            if (compensationSucceeds) {
                availableInventory++;
                audit.add("released:" + key);
                return SagaStatus.COMPENSATED;
            }
            audit.add("compensation-failed:" + key);
            return SagaStatus.NEEDS_RECONCILIATION;
        }

        public int availableInventory() {
            return availableInventory;
        }

        public List<String> audit() {
            return List.copyOf(audit);
        }
    }

    public record ApiContract(String name, Set<String> requiredFields) {
    }

    public record RetryPolicy(boolean idempotent, int maxAttempts) {
    }

    public record SecurityBoundary(String command, boolean authorizationRequired) {
    }

    public static final class ArchitectureFitness {
        public List<String> evaluate(List<String> dependencies, List<String> calls, ApiContract oldContract,
                                     ApiContract newContract, RetryPolicy retryPolicy,
                                     List<SecurityBoundary> boundaries) {
            List<String> violations = new ArrayList<>();
            dependencies.stream()
                    .filter(dep -> dep.contains(".internal."))
                    .forEach(dep -> violations.add("forbidden dependency: " + dep));
            calls.stream()
                    .filter(call -> call.endsWith("InternalRepository"))
                    .forEach(call -> violations.add("interface bypass: " + call));
            if (!newContract.requiredFields().containsAll(oldContract.requiredFields())) {
                violations.add("incompatible contract: " + oldContract.name());
            }
            if (!retryPolicy.idempotent() && retryPolicy.maxAttempts() > 1) {
                violations.add("unsafe retry configuration");
            }
            boundaries.stream()
                    .filter(boundary -> !boundary.authorizationRequired())
                    .map(SecurityBoundary::command)
                    .forEach(command -> violations.add("missing security boundary: " + command));
            return violations.stream().sorted(Comparator.naturalOrder()).toList();
        }
    }

    public static Set<String> set(String... values) {
        return new LinkedHashSet<>(List.of(values));
    }
}
