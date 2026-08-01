# Coordination Leader Election And Fencing

Coordination is the work nodes do to agree on ownership, ordering, membership, or safety. It is often necessary, but it adds latency and failure modes.

## Leaders Followers And Candidates

A leader is a node currently allowed to make a class of decisions. Followers accept decisions from a valid leader. A candidate is attempting to become leader.

Leader election chooses one leader for a term or epoch. A term is a monotonically increasing leadership generation. When a node sees a higher term, it should reject older leadership actions.

## Stale Leaders

A stale leader is a former leader that still believes it owns the role. This can happen after pauses, partitions, delayed messages, or recovery from failure. Stale leaders are dangerous when they can continue writing to shared resources.

## Fencing Tokens

A fencing token is a monotonically increasing value attached to leadership actions. A resource accepts only actions with a token at least as new as the last accepted token.

```java
record LeadershipTerm(long value) {
    boolean isNewerThan(LeadershipTerm other) {
        return value > other.value;
    }
}
```

Fencing reduces stale-leader damage only if every protected resource checks the token.

## Leases And Distributed Locks

A lease grants ownership for a time window. Leases are simpler than permanent ownership but depend on time, renewal, and expiry behavior. Clock skew and process pauses can make leases unsafe when used casually.

A distributed lock is not just a Java `Lock` over a network. Production locking requires clear ownership, expiry, fencing, persistence, failure handling, and operational visibility.

## Consensus Overview

Consensus protocols help nodes agree despite failures. Raft and Paxos are production-grade families of ideas when implemented carefully, tested deeply, and operated correctly. This curriculum discusses them only at a high level here: terms, quorums, replicated logs, leader election, and safety rules.

Educational examples in this stage are not Raft or Paxos.

## Review Questions

1. Why does a higher term cause old leadership actions to be rejected?
2. What must check a fencing token for it to be useful?
3. Why is a distributed lock more complex than a local Java lock?
