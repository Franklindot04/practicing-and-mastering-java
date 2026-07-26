# Tradeoffs Diagrams And Reviews

System design is mostly tradeoff analysis. A good design explains what it optimizes for and what it accepts as a consequence.

## Common Tradeoffs

| Choice | Helps With | Costs Or Risks |
| --- | --- | --- |
| Cache reads | Lower latency, lower storage load | Stale data, invalidation complexity |
| Add a queue | Decoupling, buffering bursts | More operations, duplicate handling |
| Split a service | Team autonomy, independent scaling | Network failure, coordination cost |
| Keep a modular monolith | Simpler deployment, local consistency | Larger codebase boundaries need discipline |
| Add read replicas | Read scale, lower primary load | Replication lag, read-after-write surprises |
| Retry failures | Recover from temporary errors | Duplicate effects, load amplification |

No row is universally good or bad. The question is whether the tradeoff fits the requirements.

## Architecture Diagrams

A beginner diagram should show boundaries, flows, and dependencies.

```text
                 +----------------+
User Request --->| Backend API    |
                 +-------+--------+
                         |
             +-----------+-----------+
             |                       |
             v                       v
       +-----+------+          +-----+------+
       | Database   |          | Async Work |
       +------------+          +-----+------+
                                      |
                                      v
                               +------+------+
                               | Notification|
                               | Boundary    |
                               +-------------+
```

Useful diagrams answer:

- What is inside the system?
- What is outside the system?
- Which path is synchronous?
- Which path is asynchronous?
- Where is data stored?
- Where can failure occur?

## Architecture Decision Records

An Architecture Decision Record, or ADR, captures an important choice.

Useful fields:

- Context.
- Decision.
- Alternatives considered.
- Consequences.
- Revisit trigger.

Example:

```text
Decision: Start with a modular monolith.
Context: One team owns the product and traffic is not yet proven.
Alternatives: Split order, payment, and notification services immediately.
Consequences: Simpler deployment now, but module boundaries must be explicit.
Revisit trigger: Separate teams or independent scaling pressure appears.
```

## Review Questions

- Which requirement does each component satisfy?
- Which component is most likely to fail?
- Which component is hardest to operate?
- Where are retries safe?
- Where is idempotency required?
- What data can be stale?
- Which dependency is on the critical path?
- What is the cheapest simpler design?
- What future change would this design make difficult?
