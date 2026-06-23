# Leader Election

Leader election is the process of choosing one participant to coordinate a task while others follow or wait.

Examples:

- One worker performs scheduled cleanup.
- One node coordinates shard assignment.
- One process owns a background import at a time.

## Why Elect A Leader

Without coordination, several instances may do the same work.

```text
Worker A runs cleanup
Worker B runs cleanup
Worker C runs cleanup
```

Leader election tries to make exactly one participant responsible.

## The Hard Part

The system must also decide what happens when the leader is slow, partitioned, or dead.

```text
Leader cannot talk to followers

Is the leader dead?
Is the network broken?
Should a new leader be elected?
Could there now be two leaders?
```

Two active leaders can be worse than no leader if both perform exclusive work.

## Beginner Rule

Do not build leader election casually. Use proven coordination systems when production requirements truly need it, and understand the failure behavior before relying on it.

