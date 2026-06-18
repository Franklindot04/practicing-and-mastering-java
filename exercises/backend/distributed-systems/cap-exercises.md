# CAP Exercises

## Exercise 1: Choose A Partition Behavior

Difficulty: Beginner

Concepts practiced: CAP theorem, consistency, availability, partition tolerance.

Problem statement:
Design the behavior for a task assignment system when two service instances cannot communicate. A user tries to assign the same task to two different people from different sides of the partition. Decide whether the system should reject one side, accept both and repair later, or use another behavior.

Hints:

- Think about user trust and ownership conflicts.
- Decide whether assignment is a critical command or a derived view.
- Explain what the user sees during the partition.

Stretch challenge:
Write a short incident note describing how operators would discover and repair a conflicting assignment.

## Exercise 2: Classify Operations

Difficulty: Beginner

Concepts practiced: operation-level CAP reasoning.

Problem statement:
Classify these operations as preferring consistency, availability, or case-by-case tradeoff during a partition: create task, update task description, mark task complete, show dashboard count, change user permissions.

Hints:

- Do not classify the whole application at once.
- Think about risk if the answer is stale.
- Think about whether repair is possible.

Stretch challenge:
Add one operation of your own and explain its tradeoff.

