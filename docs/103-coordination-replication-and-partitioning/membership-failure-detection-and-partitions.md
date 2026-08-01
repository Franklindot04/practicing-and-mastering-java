# Membership Failure Detection And Partitions

Cluster membership is the system's view of which nodes belong to the cluster and what role or state each node has. Failure detection is how nodes decide whether peers are healthy, slow, unreachable, or suspected.

## Heartbeats And Health Checks

A heartbeat is a periodic signal from one node to another. A health check asks whether a node can respond to a specific probe.

Important knobs:

- heartbeat interval
- missed-heartbeat threshold
- timeout deadline
- dependency checks included in readiness
- recovery criteria after a suspected node returns

A detector should usually say `SUSPECTED`, not `DEAD`. Missing heartbeats can mean process failure, garbage collection pause, packet loss, network congestion, or an overloaded receiver.

## Joins Leaves And Stale Membership

Node joins add capacity but can require warm-up, state transfer, and routing changes. Graceful leaves let a node drain work and transfer ownership. Unexpected failures leave peers to infer what happened.

Stale membership occurs when two nodes disagree about who belongs. Stale membership can route traffic to failed nodes or exclude healthy nodes.

## Gossip Concepts

Gossip spreads membership information peer-to-peer instead of relying on one central broadcaster. It can scale well and tolerate some failures, but convergence is not instant and conflicting observations must be reconciled.

## Network Partitions And Split Brain

A network partition divides nodes that can no longer communicate across the divide. Split brain happens when multiple sides act as if they are authoritative.

During a partition, a system must choose whether a side can continue, which operations are safe, and how results converge after healing. The Stage 24 reliability notes in [Failure Models And Fault Tolerance](../96-reliability-engineering-foundations/failure-models-and-fault-tolerance.md) provide useful background for naming these failure modes.

## Review Questions

1. Why can a heartbeat timeout be a false positive?
2. What is stale membership?
3. Why is split brain dangerous for mutable state?
