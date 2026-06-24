# CAP Solutions

## Exercise 1

A strong answer names the operation risk. Task assignment usually should avoid accepting conflicting owners. Rejecting one side or entering a read-only/degraded command mode is safer than accepting both assignments silently. If both are accepted, the system needs conflict status, repair ownership, and user-visible reconciliation.

## Exercise 2

Create task and update description are case-by-case depending on ownership and duplicate handling. Mark complete often needs read-after-write for the user and may be idempotent. Dashboard count can often prefer availability with eventual consistency. Permission changes should prefer consistency because stale permissions are risky.

