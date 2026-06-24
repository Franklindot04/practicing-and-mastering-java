# Consistency Exercises

## Exercise 1: Pick A Consistency Model

Difficulty: Beginner

Concepts practiced: strong consistency, eventual consistency, read-after-write consistency.

Problem statement:
A user creates a task. The task detail page, task list, and dashboard summary all read from different views. Decide which consistency model each view needs and explain why.

Hints:

- The user's own recent write usually needs special care.
- Summary data may be allowed to lag.
- User trust matters as much as technical correctness.

Stretch challenge:
Write the UI message you would show if the dashboard summary is delayed.

## Exercise 2: Stale Read Review

Difficulty: Intermediate

Concepts practiced: stale data, derived data, recovery.

Problem statement:
A reporting projection is five minutes behind the task database. List three user-visible problems this could cause and propose a mitigation for each.

Hints:

- Look for places where users compare screens.
- Think about timestamps and last-updated labels.
- Separate reporting from command decisions.

Stretch challenge:
Define one metric that would show projection lag.

