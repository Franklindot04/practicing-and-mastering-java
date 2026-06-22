# Distributed System Tradeoffs

Distributed systems are built from tradeoffs. A design that improves one quality may make another quality harder.

## Common Tradeoffs

| Goal | Benefit | Cost |
| --- | --- | --- |
| Split services | Independent ownership and deployment | More network calls and operational complexity |
| Add caching | Lower read latency and database load | Stale data and invalidation problems |
| Add retries | More resilience to brief failures | Duplicate work and retry storms |
| Use async messaging | Decouples producers and consumers | Harder ordering, debugging, and consistency |
| Replicate data | Better availability and read scale | Synchronization and conflict challenges |

## Simplicity Vs Flexibility

A simple monolith may be easier to understand, test, deploy, and debug. A distributed design may be more flexible when teams, scale, or reliability needs justify it.

The important question is not "Which architecture is modern?" It is "Which design fits this system's current constraints?"

## Reliability Vs Complexity

Adding more components can improve reliability only when those components are operated well. Otherwise, each added dependency becomes another way for the system to fail.

## Readiness Questions

Before distributing a capability, ask:

- Can the boundary be explained clearly?
- Who owns each service and its data?
- What failures are expected?
- How are requests traced across services?
- Which operations must be strongly consistent?
- Which operations can be eventually consistent?

