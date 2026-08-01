# Replication Lag Failover And Recovery

Replication copies state to more than one node. It can improve read capacity, durability, and availability, but it introduces lag, conflict, and recovery questions.

## Replication Models

Primary-replica replication routes writes through one owner, then copies changes to replicas. Multi-leader replication allows multiple write owners and must resolve conflicts. Leaderless replication accepts writes on multiple replicas according to quorum rules.

Synchronous replication waits for replica acknowledgements before success. Asynchronous replication returns before all replicas catch up.

## Lag And Stale Replicas

Replication lag is the delay between a write on one node and visibility on another. Reads from lagging replicas may be stale.

Lag matters when:

- users expect read-your-writes
- failover promotes a behind replica
- reports mix old and new values
- duplicate actions are generated from stale state

## Conflict Resolution And Read Repair

Read repair updates stale replicas when a newer value is observed during reads. Conflict resolution decides what to do when replicas accepted different values. Common teaching choices include version checks, last-write-wins, merge rules, or manual repair. Each has business consequences.

## Write Acknowledgements And Quorums

Write acknowledgement policy controls when the caller sees success. Waiting for more replicas can reduce data loss after failure but increases latency and can reduce availability.

Quorum reads and writes try to overlap successful write participants with later read participants. They still require careful membership, repair, and failure handling.

## Failover And Recovery

Failover chooses a replacement when the primary fails. Recovery brings a failed node back, verifies its state, catches it up, and returns it to service.

Promotion risks:

- a stale replica becomes leader
- old leader returns and accepts writes
- clients route to different leaders
- recovery replays old data over newer data

## Review Questions

1. Why can asynchronous replication produce stale reads?
2. What can go wrong when promoting a lagging replica?
3. Why does conflict resolution need business rules?
