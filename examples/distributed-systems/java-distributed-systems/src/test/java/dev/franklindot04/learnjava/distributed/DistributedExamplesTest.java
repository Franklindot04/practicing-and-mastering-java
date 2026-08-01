package dev.franklindot04.learnjava.distributed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class DistributedExamplesTest {
    @Test
    void heartbeatDetectorSuspectsDelayedNodeAndRecovers() {
        SimulationClock clock = new SimulationClock();
        HeartbeatFailureDetector detector = new HeartbeatFailureDetector(clock, Duration.ofMillis(100));
        NodeId node = new NodeId("node-a");

        detector.heartbeatFrom(node);
        assertEquals(HealthStatus.HEALTHY, detector.statusOf(node));

        clock.advance(Duration.ofMillis(101));
        assertEquals(HealthStatus.SUSPECTED, detector.statusOf(node));

        detector.heartbeatFrom(node);
        assertEquals(HealthStatus.HEALTHY, detector.statusOf(node));
    }

    @Test
    void lamportClockOrdersCausalReceiveButCannotOrderConcurrentEvents() {
        LamportClock a = new LamportClock("a");
        LamportClock b = new LamportClock("b");

        LogicalEvent sent = a.sendEvent();
        LogicalEvent received = b.receive(sent);
        LogicalEvent concurrent = a.localEvent();

        assertTrue(sent.time() < received.time());
        assertEquals(concurrent.time(), received.time());
    }

    @Test
    void educationalElectionUsesTermsAndRejectsStaleLeadership() {
        NodeId a = new NodeId("a");
        NodeId b = new NodeId("b");
        EducationalLeaderElection election = new EducationalLeaderElection(List.of(a, b));

        Leadership first = election.electLeader();
        election.fail(first.leaderId());
        Leadership second = election.electLeader();

        assertTrue(second.term().value() > first.term().value());
        assertFalse(election.accepts(first.term()));
        assertTrue(election.accepts(second.term()));
    }

    @Test
    void replicatedStoreShowsLagStaleReadCatchUpAndPromotionRisk() {
        NodeId replica = new NodeId("replica-a");
        ReplicatedKeyValueStore store = new ReplicatedKeyValueStore(List.of(replica));

        store.write("order-1", "CREATED");
        assertEquals("CREATED", store.readPrimary("order-1").orElseThrow());
        assertTrue(store.readReplica(replica, "order-1").isEmpty());
        assertTrue(store.promoteReplica(replica).isEmpty());

        store.catchUp();
        assertEquals("CREATED", store.readReplica(replica, "order-1").orElseThrow());
    }

    @Test
    void consistentHashingMovesFewerKeysThanModuloWhenNodeIsAdded() {
        List<String> keys = IntStream.range(0, 200)
                .mapToObj(index -> "customer-" + index)
                .toList();
        List<NodeId> before = List.of(new NodeId("a"), new NodeId("b"), new NodeId("c"));
        List<NodeId> after = List.of(new NodeId("a"), new NodeId("b"), new NodeId("c"), new NodeId("d"));

        ReassignmentReport report = ConsistentHashRing.compareMovement(keys, before, after);

        assertTrue(report.consistentHashMoved() < report.moduloMoved());
    }
}
