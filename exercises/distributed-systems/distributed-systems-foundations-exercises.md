# Distributed Systems Foundations Exercises

## 1. Identify Partial Failures

Objective: distinguish full failure from partial failure.

Scenario: an order API times out while calling payment, but payment may still commit.

Requirements: list at least four possible outcomes, identify what evidence is missing, and describe what the caller may safely tell the user.

Constraints: do not assume timeout means rollback.

Expected reasoning: a timeout is an unknown outcome; observability and idempotency are needed.

Acceptance criteria: explains committed response lost, request never arrived, request rejected, and request still running.

Optional extension: design a status-check endpoint.

## 2. Local Versus Distributed Guarantees

Objective: explain why JVM guarantees do not cross process boundaries.

Scenario: a synchronized Java method updates local memory and then calls another service.

Requirements: identify which guarantees are local and which are distributed assumptions.

Constraints: avoid claiming that `synchronized` protects remote state.

Expected reasoning: local mutual exclusion does not serialize remote writes.

Acceptance criteria: names one local lock guarantee and one remote failure case.

## 3. Message Loss Duplication And Reordering

Objective: reason about unreliable delivery.

Scenario: `AddressChanged` and `OrderShipped` are sent during a retry storm.

Requirements: describe loss, duplicate delivery, and reordered delivery outcomes.

Constraints: do not add a broker or external system.

Expected reasoning: handlers need idempotency, ordering checks, or safe state transitions.

Acceptance criteria: includes at least one duplicate-safe design.

## 4. Classify Consistency Requirements

Objective: choose consistency models based on business risk.

Scenario: compare shopping-cart display, account balance transfer, and profile photo update.

Requirements: classify each as strong, read-your-writes, monotonic reads, causal, or eventual consistency.

Constraints: justify choices with user impact.

Expected reasoning: different features tolerate stale data differently.

Acceptance criteria: identifies one stale-read consequence per feature.

## 5. Simple Quorum Reasoning

Objective: calculate conceptual quorum overlap.

Scenario: replication factor is five.

Requirements: propose read and write quorum sizes and explain whether they overlap.

Constraints: state that the formula is educational, not a complete protocol.

Expected reasoning: `R + W > N` creates overlap.

Acceptance criteria: includes at least two trade-off combinations.

## 6. Clock-Skew Failure

Objective: identify physical time risks.

Scenario: node A grants a 30-second lease, but node B's clock is 12 seconds ahead.

Requirements: explain how skew can create stale ownership or premature expiry.

Constraints: do not rely on wall-clock timestamps for causal order.

Expected reasoning: leases require conservative expiry and fencing.

Acceptance criteria: includes one mitigation.

## 7. Implement A Heartbeat Domain Model

Objective: model node identity and heartbeats.

Scenario: create `NodeId`, `Heartbeat`, and `NodeStatus`.

Requirements: validate blank node IDs, record last-seen time, and report `HEALTHY` or `SUSPECTED`.

Constraints: use injected or simulated time, not `Thread.sleep`.

Expected reasoning: missing heartbeats are suspicion, not proof.

Acceptance criteria: deterministic tests cover healthy, timeout, recovery, and false-suspicion risk.

## 8. Implement A Lamport Clock

Objective: model logical ordering.

Scenario: two nodes exchange messages.

Requirements: implement local, send, and receive events with the Lamport update rule.

Constraints: do not use wall-clock time.

Expected reasoning: causal send before receive is ordered; concurrent events may remain ambiguous.

Acceptance criteria: tests cover receive update and concurrent equal or incomparable events.

## 9. Model Membership Transitions

Objective: define valid node lifecycle changes.

Scenario: nodes can start, become healthy, be suspected, fail, recover, and stop.

Requirements: reject invalid transitions and document recovery requirements.

Constraints: keep transitions explicit.

Expected reasoning: recovery must verify state before returning to healthy.

Acceptance criteria: tests cover join, duplicate join, failure, recovery, and invalid transition.

## 10. Reject Stale Leadership Terms

Objective: prevent stale leaders from acting.

Scenario: a former leader returns after a partition with term 3 while the cluster is at term 4.

Requirements: define `LeadershipTerm` and reject commands carrying old terms.

Constraints: do not claim this is Raft or Paxos.

Expected reasoning: terms reduce stale-leader writes only when checked by protected resources.

Acceptance criteria: tests cover higher term election and stale-term rejection.

## 11. Reason About Fencing Tokens

Objective: explain why terms alone are not enough.

Scenario: a storage resource receives writes from old and new leaders.

Requirements: design a fencing-token check at the resource boundary.

Constraints: every write path must check the token.

Expected reasoning: stale leaders are blocked by monotonically increasing accepted tokens.

Acceptance criteria: explains what happens if one path forgets the check.

## 12. Implement Basic Replication

Objective: model primary and replicas.

Scenario: a primary accepts writes and replicas catch up later.

Requirements: implement write, read primary, read replica, pending replication, and catch-up.

Constraints: asynchronous lag must be observable.

Expected reasoning: stale reads are expected before catch-up.

Acceptance criteria: tests cover write, lag, stale read, catch-up, and promotion risk.

## 13. Detect Replication Lag

Objective: expose lag as an operational signal.

Scenario: a dashboard needs to show whether replicas are behind.

Requirements: track pending operation count or version difference.

Constraints: do not hide lag behind successful writes.

Expected reasoning: writes can be accepted while replicas are stale.

Acceptance criteria: includes metric name, threshold, and incident response.

## 14. Partition Keys

Objective: route keys to owners.

Scenario: users are assigned to partitions.

Requirements: implement modulo partitioning and explain what happens when partition count changes.

Constraints: use stable key values.

Expected reasoning: modulo is simple but moves many keys during resize.

Acceptance criteria: test shows reassignment after adding a partition.

## 15. Compare Modulo And Consistent Hashing

Objective: evaluate data movement.

Scenario: add one node to a three-node cluster.

Requirements: compute reassignment counts for modulo and consistent hashing.

Constraints: keep hashing deterministic.

Expected reasoning: consistent hashing usually moves fewer keys.

Acceptance criteria: report includes moved-key counts and limitations.

## 16. Analyze Hot Partitions

Objective: detect skew.

Scenario: one tenant generates 60 percent of requests.

Requirements: identify symptoms, metrics, and mitigation options.

Constraints: preserve business ownership rules.

Expected reasoning: even distribution by key count may not mean even load.

Acceptance criteria: proposes at least two mitigations and one risk.

## 17. Analyze Split-Brain Risk

Objective: reason about partitions and authority.

Scenario: a five-node cluster splits into two and three nodes.

Requirements: decide which side can safely accept authoritative writes and why.

Constraints: use quorum concepts, not wishful thinking.

Expected reasoning: minority partition should usually reject authoritative writes.

Acceptance criteria: explains convergence after healing.

## 18. Design Recovery Verification

Objective: verify more than process startup.

Scenario: a failed node rejoins after missing writes.

Requirements: list checks before marking it healthy.

Constraints: include membership, state version, lag, and leadership term.

Expected reasoning: recovery needs synchronization and validation.

Acceptance criteria: produces a recovery checklist.

## 19. Design Operational Metrics

Objective: make distributed failure visible.

Scenario: operators need a Stage 25 dashboard.

Requirements: select node-level and cluster-level metrics.

Constraints: include leader changes, quorum health, replication lag, partition indicators, and correlation IDs.

Expected reasoning: local health is insufficient.

Acceptance criteria: includes alert candidates and runbook links to write later.

## 20. Review A Failure Model

Objective: state assumptions honestly.

Scenario: a team claims its simulator provides exactly-once writes.

Requirements: critique the claim and rewrite it accurately.

Constraints: do not use impossible guarantees.

Expected reasoning: educational simulations can show duplicate handling but not exactly-once delivery.

Acceptance criteria: replacement wording states simplifications and production gaps.
