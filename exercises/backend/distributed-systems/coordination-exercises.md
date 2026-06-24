# Coordination Exercises

## Exercise 1: Scheduled Cleanup Ownership

Difficulty: Intermediate

Concepts practiced: leader election, duplicate work, idempotency.

Problem statement:
Three task API instances can run scheduled cleanup. Design a safe approach so cleanup does not damage data if more than one instance runs it.

Hints:

- Ask whether cleanup can be idempotent.
- Avoid distributed locks if a simpler design works.
- Think about late writes from stale leaders.

Stretch challenge:
Add a fencing-token concept to your design.

## Exercise 2: Distributed Lock Review

Difficulty: Intermediate

Concepts practiced: distributed locks, leases, failure handling.

Problem statement:
A worker obtains a distributed lock for 30 seconds, pauses for 45 seconds, then resumes and writes results. Explain why this is dangerous and how the design could reject stale work.

Hints:

- The lock may have expired while the worker paused.
- Another worker may have taken over.
- Fencing tokens can help downstream systems reject old owners.

Stretch challenge:
Write a short timeline showing both workers.

