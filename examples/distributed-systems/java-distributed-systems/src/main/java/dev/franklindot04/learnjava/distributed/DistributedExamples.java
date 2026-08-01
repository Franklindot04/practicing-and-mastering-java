package dev.franklindot04.learnjava.distributed;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

record NodeId(String value) implements Comparable<NodeId> {
    NodeId {
        Objects.requireNonNull(value, "value");
        if (value.isBlank()) {
            throw new IllegalArgumentException("node id must not be blank");
        }
    }

    @Override
    public int compareTo(NodeId other) {
        return value.compareTo(other.value);
    }
}

record Heartbeat(NodeId nodeId, long observedAtMillis) { }

enum HealthStatus {
    HEALTHY,
    SUSPECTED
}

final class SimulationClock {
    private long millis;

    long nowMillis() {
        return millis;
    }

    void advance(Duration duration) {
        if (duration.isNegative()) {
            throw new IllegalArgumentException("duration must not be negative");
        }
        millis += duration.toMillis();
    }
}

final class HeartbeatFailureDetector {
    private final SimulationClock clock;
    private final long timeoutMillis;
    private final Map<NodeId, Long> lastSeenMillis = new HashMap<>();

    HeartbeatFailureDetector(SimulationClock clock, Duration timeout) {
        this.clock = clock;
        this.timeoutMillis = timeout.toMillis();
    }

    Heartbeat heartbeatFrom(NodeId nodeId) {
        Heartbeat heartbeat = new Heartbeat(nodeId, clock.nowMillis());
        receive(heartbeat);
        return heartbeat;
    }

    void receive(Heartbeat heartbeat) {
        lastSeenMillis.put(heartbeat.nodeId(), heartbeat.observedAtMillis());
    }

    HealthStatus statusOf(NodeId nodeId) {
        Long lastSeen = lastSeenMillis.get(nodeId);
        if (lastSeen == null || clock.nowMillis() - lastSeen > timeoutMillis) {
            return HealthStatus.SUSPECTED;
        }
        return HealthStatus.HEALTHY;
    }
}

record LogicalEvent(String node, long time) { }

final class LamportClock {
    private final String node;
    private long value;

    LamportClock(String node) {
        this.node = node;
    }

    LogicalEvent localEvent() {
        return new LogicalEvent(node, ++value);
    }

    LogicalEvent sendEvent() {
        return localEvent();
    }

    LogicalEvent receive(LogicalEvent received) {
        value = Math.max(value, received.time()) + 1;
        return new LogicalEvent(node, value);
    }

    long value() {
        return value;
    }
}

record LeadershipTerm(long value) implements Comparable<LeadershipTerm> {
    @Override
    public int compareTo(LeadershipTerm other) {
        return Long.compare(value, other.value);
    }
}

record Leadership(NodeId leaderId, LeadershipTerm term) { }

final class EducationalLeaderElection {
    private final Set<NodeId> availableNodes = new HashSet<>();
    private LeadershipTerm currentTerm = new LeadershipTerm(0);
    private Leadership leadership;

    EducationalLeaderElection(Collection<NodeId> nodes) {
        availableNodes.addAll(nodes);
    }

    Leadership electLeader() {
        NodeId winner = availableNodes.stream().min(NodeId::compareTo)
                .orElseThrow(() -> new IllegalStateException("no available nodes"));
        currentTerm = new LeadershipTerm(currentTerm.value() + 1);
        leadership = new Leadership(winner, currentTerm);
        return leadership;
    }

    void fail(NodeId nodeId) {
        availableNodes.remove(nodeId);
        if (leadership != null && leadership.leaderId().equals(nodeId)) {
            leadership = null;
        }
    }

    boolean accepts(LeadershipTerm term) {
        return leadership != null && term.compareTo(leadership.term()) >= 0;
    }
}

record WriteOperation(String key, String value, long version) { }

final class ReplicatedKeyValueStore {
    private final Map<String, String> primary = new HashMap<>();
    private final Map<NodeId, Map<String, String>> replicas = new HashMap<>();
    private final Queue<WriteOperation> pendingReplication = new ArrayDeque<>();
    private long version;

    ReplicatedKeyValueStore(Collection<NodeId> replicaIds) {
        replicaIds.forEach(id -> replicas.put(id, new HashMap<>()));
    }

    WriteOperation write(String key, String value) {
        primary.put(key, value);
        WriteOperation operation = new WriteOperation(key, value, ++version);
        pendingReplication.add(operation);
        return operation;
    }

    Optional<String> readPrimary(String key) {
        return Optional.ofNullable(primary.get(key));
    }

    Optional<String> readReplica(NodeId replicaId, String key) {
        return Optional.ofNullable(replicas.getOrDefault(replicaId, Map.of()).get(key));
    }

    void replicateNext() {
        WriteOperation operation = pendingReplication.poll();
        if (operation != null) {
            replicas.values().forEach(replica -> replica.put(operation.key(), operation.value()));
        }
    }

    void catchUp() {
        while (!pendingReplication.isEmpty()) {
            replicateNext();
        }
    }

    Map<String, String> promoteReplica(NodeId replicaId) {
        return new HashMap<>(replicas.get(replicaId));
    }
}

record ReassignmentReport(int moduloMoved, int consistentHashMoved) { }

final class ConsistentHashRing {
    private final int virtualNodes;
    private final NavigableMap<Integer, NodeId> ring = new TreeMap<>();

    ConsistentHashRing(int virtualNodes) {
        this.virtualNodes = virtualNodes;
    }

    void add(NodeId nodeId) {
        for (int i = 0; i < virtualNodes; i++) {
            ring.put(hash(nodeId.value() + "#" + i), nodeId);
        }
    }

    void remove(NodeId nodeId) {
        ring.entrySet().removeIf(entry -> entry.getValue().equals(nodeId));
    }

    NodeId ownerOf(String key) {
        if (ring.isEmpty()) {
            throw new IllegalStateException("ring is empty");
        }
        int hash = hash(key);
        Map.Entry<Integer, NodeId> entry = ring.ceilingEntry(hash);
        return entry == null ? ring.firstEntry().getValue() : entry.getValue();
    }

    static NodeId moduloOwner(String key, List<NodeId> nodes) {
        List<NodeId> ordered = new ArrayList<>(nodes);
        Collections.sort(ordered);
        return ordered.get(Math.floorMod(key.hashCode(), ordered.size()));
    }

    static ReassignmentReport compareMovement(List<String> keys, List<NodeId> before, List<NodeId> after) {
        int moduloMoved = 0;
        int consistentMoved = 0;
        ConsistentHashRing beforeRing = ringWith(before);
        ConsistentHashRing afterRing = ringWith(after);
        for (String key : keys) {
            if (!moduloOwner(key, before).equals(moduloOwner(key, after))) {
                moduloMoved++;
            }
            if (!beforeRing.ownerOf(key).equals(afterRing.ownerOf(key))) {
                consistentMoved++;
            }
        }
        return new ReassignmentReport(moduloMoved, consistentMoved);
    }

    private static ConsistentHashRing ringWith(List<NodeId> nodes) {
        ConsistentHashRing ring = new ConsistentHashRing(25);
        nodes.forEach(ring::add);
        return ring;
    }

    private static int hash(String value) {
        return value.hashCode() & 0x7fffffff;
    }
}
