package dev.franklindot04.learnjava.distributedsim;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

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

enum NodeState {
    STARTING,
    HEALTHY,
    SUSPECTED,
    PARTITIONED,
    FAILED,
    RECOVERING,
    STOPPED
}

record LeadershipTerm(long value) implements Comparable<LeadershipTerm> {
    @Override
    public int compareTo(LeadershipTerm other) {
        return Long.compare(value, other.value);
    }
}

record Heartbeat(NodeId from, NodeId to, long sentAtMillis) { }

record ClusterEvent(long sequence, long atMillis, String description) { }

record SimulationReport(List<ClusterEvent> events, Map<NodeId, NodeState> states, Optional<NodeId> leader) { }

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

final class ClusterNode {
    private final NodeId id;
    private NodeState state = NodeState.STARTING;
    private LeadershipTerm term = new LeadershipTerm(0);

    ClusterNode(NodeId id) {
        this.id = id;
    }

    NodeId id() {
        return id;
    }

    NodeState state() {
        return state;
    }

    void transitionTo(NodeState next) {
        if (state == NodeState.STOPPED && next != NodeState.STARTING) {
            throw new IllegalStateException("stopped node must start before " + next);
        }
        state = next;
    }

    LeadershipTerm term() {
        return term;
    }

    void observeTerm(LeadershipTerm observed) {
        if (observed.compareTo(term) > 0) {
            term = observed;
        }
    }
}

final class MembershipRegistry {
    private final Map<NodeId, ClusterNode> nodes = new LinkedHashMap<>();

    void register(NodeId nodeId) {
        if (nodes.containsKey(nodeId)) {
            throw new IllegalArgumentException("duplicate node registration: " + nodeId.value());
        }
        ClusterNode node = new ClusterNode(nodeId);
        node.transitionTo(NodeState.HEALTHY);
        nodes.put(nodeId, node);
    }

    Collection<ClusterNode> nodes() {
        return nodes.values();
    }

    ClusterNode require(NodeId nodeId) {
        ClusterNode node = nodes.get(nodeId);
        if (node == null) {
            throw new IllegalArgumentException("unknown node: " + nodeId.value());
        }
        return node;
    }

    Map<NodeId, NodeState> states() {
        Map<NodeId, NodeState> states = new LinkedHashMap<>();
        nodes.forEach((id, node) -> states.put(id, node.state()));
        return states;
    }
}

final class FailureDetector {
    private final SimulationClock clock;
    private final Duration timeout;
    private final Map<NodeId, Long> lastSeen = new HashMap<>();

    FailureDetector(SimulationClock clock, Duration timeout) {
        this.clock = clock;
        this.timeout = timeout;
    }

    void record(Heartbeat heartbeat) {
        lastSeen.put(heartbeat.from(), heartbeat.sentAtMillis());
    }

    boolean isSuspected(NodeId nodeId) {
        Long last = lastSeen.get(nodeId);
        return last == null || clock.nowMillis() - last > timeout.toMillis();
    }
}

final class NetworkSimulator {
    private final Set<String> droppedLinks = new HashSet<>();
    private final Queue<Heartbeat> delayed = new ArrayDeque<>();

    void partition(NodeId a, NodeId b) {
        droppedLinks.add(key(a, b));
        droppedLinks.add(key(b, a));
    }

    void heal() {
        droppedLinks.clear();
    }

    boolean canSend(NodeId from, NodeId to) {
        return !droppedLinks.contains(key(from, to));
    }

    void delay(Heartbeat heartbeat) {
        delayed.add(heartbeat);
    }

    List<Heartbeat> releaseDelayed() {
        List<Heartbeat> released = new ArrayList<>(delayed);
        delayed.clear();
        return released;
    }

    private String key(NodeId from, NodeId to) {
        return from.value() + "->" + to.value();
    }
}

final class ElectionCoordinator {
    private LeadershipTerm term = new LeadershipTerm(0);
    private NodeId leader;

    NodeId elect(Collection<ClusterNode> nodes) {
        List<ClusterNode> eligible = nodes.stream()
                .filter(node -> node.state() == NodeState.HEALTHY || node.state() == NodeState.RECOVERING)
                .sorted(Comparator.comparing(ClusterNode::id))
                .toList();
        if (eligible.isEmpty()) {
            throw new IllegalStateException("no eligible leader");
        }
        term = new LeadershipTerm(term.value() + 1);
        leader = eligible.get(0).id();
        eligible.forEach(node -> node.observeTerm(term));
        return leader;
    }

    boolean accepts(LeadershipTerm candidateTerm) {
        return candidateTerm.compareTo(term) >= 0;
    }

    LeadershipTerm term() {
        return term;
    }

    Optional<NodeId> leader() {
        return Optional.ofNullable(leader);
    }
}

record ReplicationOperation(String key, String value, LeadershipTerm term) { }

final class ReplicatedState {
    private final Map<NodeId, Map<String, String>> nodeData = new HashMap<>();
    private final Queue<ReplicationOperation> lag = new ArrayDeque<>();

    void addNode(NodeId nodeId) {
        nodeData.putIfAbsent(nodeId, new HashMap<>());
    }

    void write(NodeId leader, String key, String value, LeadershipTerm term) {
        nodeData.get(leader).put(key, value);
        lag.add(new ReplicationOperation(key, value, term));
    }

    Optional<String> read(NodeId nodeId, String key) {
        return Optional.ofNullable(nodeData.getOrDefault(nodeId, Map.of()).get(key));
    }

    void replicateOne(Collection<NodeId> nodes) {
        ReplicationOperation operation = lag.poll();
        if (operation != null) {
            nodes.forEach(id -> nodeData.get(id).put(operation.key(), operation.value()));
        }
    }

    void catchUp(Collection<NodeId> nodes) {
        while (!lag.isEmpty()) {
            replicateOne(nodes);
        }
    }

    boolean hasLag() {
        return !lag.isEmpty();
    }
}

final class Cluster {
    private final SimulationClock clock = new SimulationClock();
    private final MembershipRegistry membership = new MembershipRegistry();
    private final FailureDetector detector = new FailureDetector(clock, Duration.ofMillis(100));
    private final NetworkSimulator network = new NetworkSimulator();
    private final ElectionCoordinator election = new ElectionCoordinator();
    private final ReplicatedState state = new ReplicatedState();
    private final List<ClusterEvent> events = new ArrayList<>();
    private long sequence;

    void join(NodeId nodeId) {
        membership.register(nodeId);
        state.addNode(nodeId);
        log(nodeId.value() + " joined");
    }

    void heartbeat(NodeId from, NodeId to) {
        Heartbeat heartbeat = new Heartbeat(from, to, clock.nowMillis());
        if (network.canSend(from, to)) {
            detector.record(heartbeat);
            membership.require(from).transitionTo(NodeState.HEALTHY);
            log("heartbeat " + from.value() + " -> " + to.value());
        } else {
            network.delay(heartbeat);
            membership.require(from).transitionTo(NodeState.PARTITIONED);
            log("delayed heartbeat " + from.value() + " -> " + to.value());
        }
    }

    void advance(Duration duration) {
        clock.advance(duration);
        for (ClusterNode node : membership.nodes()) {
            if (node.state() != NodeState.FAILED && detector.isSuspected(node.id())) {
                node.transitionTo(NodeState.SUSPECTED);
            }
        }
        log("advanced " + duration.toMillis() + "ms");
    }

    NodeId electLeader() {
        NodeId leader = election.elect(membership.nodes());
        log("leader " + leader.value() + " term " + election.term().value());
        return leader;
    }

    void fail(NodeId nodeId) {
        membership.require(nodeId).transitionTo(NodeState.FAILED);
        log(nodeId.value() + " failed");
    }

    void recover(NodeId nodeId) {
        membership.require(nodeId).transitionTo(NodeState.RECOVERING);
        state.catchUp(nodeIds());
        membership.require(nodeId).transitionTo(NodeState.HEALTHY);
        log(nodeId.value() + " recovered");
    }

    void partition(NodeId a, NodeId b) {
        network.partition(a, b);
        membership.require(a).transitionTo(NodeState.PARTITIONED);
        membership.require(b).transitionTo(NodeState.PARTITIONED);
        log("partition " + a.value() + " " + b.value());
    }

    void healPartition() {
        network.heal();
        network.releaseDelayed().forEach(detector::record);
        membership.nodes().forEach(node -> {
            if (node.state() == NodeState.PARTITIONED || node.state() == NodeState.SUSPECTED) {
                node.transitionTo(NodeState.HEALTHY);
            }
        });
        log("partition healed");
    }

    void write(NodeId leader, String key, String value, LeadershipTerm term) {
        if (!election.accepts(term)) {
            throw new IllegalStateException("stale leadership term rejected");
        }
        state.write(leader, key, value, term);
        log("write " + key + "=" + value);
    }

    void replicateOne() {
        state.replicateOne(nodeIds());
        log("replicated one operation");
    }

    void catchUpReplicas() {
        state.catchUp(nodeIds());
        log("replicas caught up");
    }

    Optional<String> read(NodeId nodeId, String key) {
        return state.read(nodeId, key);
    }

    boolean hasReplicationLag() {
        return state.hasLag();
    }

    LeadershipTerm currentTerm() {
        return election.term();
    }

    SimulationReport report() {
        return new SimulationReport(List.copyOf(events), membership.states(), election.leader());
    }

    private List<NodeId> nodeIds() {
        return membership.nodes().stream().map(ClusterNode::id).toList();
    }

    private void log(String description) {
        events.add(new ClusterEvent(++sequence, clock.nowMillis(), description));
    }
}
