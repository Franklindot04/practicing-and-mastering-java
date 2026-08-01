package dev.franklindot04.learnjava.distributedsim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class ClusterSimulatorTest {
    private final NodeId a = new NodeId("a");
    private final NodeId b = new NodeId("b");
    private final NodeId c = new NodeId("c");

    @Test
    void healthyClusterElectsLeaderAndReplicatesState() {
        Cluster cluster = threeNodeCluster();
        cluster.heartbeat(a, b);
        NodeId leader = cluster.electLeader();
        LeadershipTerm term = cluster.currentTerm();

        cluster.write(leader, "mode", "healthy", term);
        assertTrue(cluster.hasReplicationLag());
        cluster.catchUpReplicas();

        assertFalse(cluster.hasReplicationLag());
        assertEquals("healthy", cluster.read(c, "mode").orElseThrow());
        assertTrue(cluster.report().leader().isPresent());
    }

    @Test
    void heartbeatTimeoutFalseSuspicionAndRecoveryAreDeterministic() {
        Cluster cluster = threeNodeCluster();
        cluster.heartbeat(a, b);
        cluster.advance(Duration.ofMillis(101));

        assertEquals(NodeState.SUSPECTED, cluster.report().states().get(a));

        cluster.heartbeat(a, b);
        assertEquals(NodeState.HEALTHY, cluster.report().states().get(a));
    }

    @Test
    void leaderFailureCreatesHigherTermAndRejectsStaleTerm() {
        Cluster cluster = threeNodeCluster();
        NodeId oldLeader = cluster.electLeader();
        LeadershipTerm oldTerm = cluster.currentTerm();

        cluster.fail(oldLeader);
        NodeId newLeader = cluster.electLeader();
        LeadershipTerm newTerm = cluster.currentTerm();

        assertTrue(newTerm.value() > oldTerm.value());
        assertThrows(IllegalStateException.class, () -> cluster.write(newLeader, "x", "old", oldTerm));
        cluster.write(newLeader, "x", "new", newTerm);
    }

    @Test
    void partitionHealingRestoresHealthyMembership() {
        Cluster cluster = threeNodeCluster();
        cluster.partition(a, c);
        cluster.heartbeat(a, c);

        assertEquals(NodeState.PARTITIONED, cluster.report().states().get(a));

        cluster.healPartition();
        assertEquals(NodeState.HEALTHY, cluster.report().states().get(a));
        assertEquals(NodeState.HEALTHY, cluster.report().states().get(c));
    }

    @Test
    void replicaLagStaleReadCatchUpAndNodeRecoveryAreVisible() {
        Cluster cluster = threeNodeCluster();
        NodeId leader = cluster.electLeader();
        LeadershipTerm term = cluster.currentTerm();

        cluster.write(leader, "order-1", "CREATED", term);
        assertTrue(cluster.read(b, "order-1").isEmpty());
        cluster.replicateOne();
        assertEquals("CREATED", cluster.read(b, "order-1").orElseThrow());

        cluster.fail(c);
        cluster.write(leader, "order-1", "PAID", term);
        cluster.recover(c);
        assertEquals("PAID", cluster.read(c, "order-1").orElseThrow());
    }

    @Test
    void duplicateRegistrationInvalidTransitionAndEventOrderingAreChecked() {
        Cluster cluster = new Cluster();
        cluster.join(a);

        assertThrows(IllegalArgumentException.class, () -> cluster.join(a));

        ClusterNode stopped = new ClusterNode(new NodeId("z"));
        stopped.transitionTo(NodeState.STOPPED);
        assertThrows(IllegalStateException.class, () -> stopped.transitionTo(NodeState.HEALTHY));

        cluster.join(b);
        cluster.heartbeat(a, b);
        assertEquals(3, cluster.report().events().get(2).sequence());
    }

    private Cluster threeNodeCluster() {
        Cluster cluster = new Cluster();
        cluster.join(a);
        cluster.join(b);
        cluster.join(c);
        return cluster;
    }
}
