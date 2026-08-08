# Lease and Heartbeat Model

Workers register with capacity and partition ownership. Leases include a worker id and expiration time. Expired leases return tasks to ready state with incremented attempts so another worker can safely retry.

## Purpose

Leases prevent two active workers from claiming the same task at the same time in the simulation. The model also shows why worker failure cannot be handled by immediate reassignment alone: the system must wait until lease expiration or have trustworthy heartbeat evidence.

## Assumptions

- The platform uses deterministic time in tests.
- A lease belongs to one worker and one task.
- Completion must present the expected worker id.
- Side effects must be idempotent or guarded because a crashed worker may have partially executed work.

## Trade-Offs

Short leases recover faster but increase duplicate-execution risk if legitimate work takes longer than expected. Long leases reduce duplicate work but delay recovery. Production systems often combine leases with heartbeats, progress checkpoints, and task-specific timeout budgets.

## Failure Behaviour

When a lease expires, the task returns to ready state and its attempt count increments. Completion after expiration should be rejected unless a valid current lease exists. Duplicate completion is treated separately through completion idempotency keys.

## Production Comparison

A deployed workflow system would persist leases, heartbeat timestamps, worker identity, and task state durably. It would also handle clock skew, worker partitions, process pauses, and lease renewal failures. The capstone models the decision points but does not implement those distributed guarantees.
