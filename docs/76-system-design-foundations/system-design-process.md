# System Design Process

System design means describing how software components, data, people, and operational responsibilities work together to satisfy a goal. For a Java backend developer, it is the bridge between coding individual services and reasoning about the whole backend system.

## Why It Matters

Backend systems rarely fail because one class has a bad name. They fail because boundaries are unclear, workloads are misunderstood, data ownership is fuzzy, or failure behavior was never discussed.

System design helps you:

- Turn vague goals into reviewable requirements.
- Choose simple architecture before complex architecture.
- Find expensive or fragile assumptions early.
- Explain tradeoffs to teammates.
- Connect code-level decisions to reliability, security, operations, and cost.

## A Beginner-Friendly Process

Use this sequence before naming specific tools:

1. Clarify the goal.
2. Identify users and actors.
3. List functional requirements.
4. List non-functional requirements.
5. Capture constraints and assumptions.
6. Sketch read and write workflows.
7. Identify data, state changes, and ownership.
8. Draw the first boundary diagram.
9. Discuss failure assumptions.
10. Compare alternatives and tradeoffs.
11. Write review questions.
12. Record decisions that should not be forgotten.

## Simple Example

Goal: users can create tasks and later view them.

Functional requirements:

- Create a task.
- List tasks for a user.
- Mark a task complete.

Non-functional requirements:

- Reads should feel fast for normal user traffic.
- Writes should not create duplicate tasks when the user retries.
- The system should return safe errors.

Possible first design:

```text
Browser
  |
  v
Task API
  |
  v
Task Storage
```

This is enough for the first review. Adding queues, caches, replicas, and service meshes before understanding traffic and failure goals would be premature.

## Design Review Questions

- What is the smallest design that satisfies the stated requirements?
- Which requirement forced each major component to exist?
- Which assumptions need measurement?
- Which failure is most likely?
- Which failure is most harmful?
- What would you remove if traffic is lower than expected?
- What would you add if traffic is higher than expected?
