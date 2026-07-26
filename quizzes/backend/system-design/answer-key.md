# Answer Key

## Multiple Choice

1. B
2. B
3. B
4. B
5. A
6. B
7. B
8. B

## Short Answer Highlights

Good answers should distinguish behavior from quality attributes, measurements from assumptions, and design choices from universal rules.

- Non-functional examples: latency, availability, durability, security, cost, observability.
- "Fast search" needs actors, query fields, data freshness, authorization, result size, and latency target.
- Scalability is growth handling; performance is current speed for a workload.
- Consistency is what reads observe after writes; durability is whether committed data survives failure.
- Retries can amplify load and duplicate side effects without idempotency.
- Good boundaries align ownership, data, behavior, and failure isolation.

## Architecture Comparison Highlights

Reasonable answers may choose different designs if assumptions differ. Strong answers name why the chosen design fits the current team, workload, consistency, and operations constraints.

## Capacity Estimation Highlights

- 2,000,000 reads per day is about 23.1 reads per second on average.
- 100,000 writes per day is about 1.16 writes per second on average.
- Daily averages hide peaks, hot tenants, and burst behavior.
- Monitor request rate, latency, errors, saturation, queue depth, database load, and cost.

## Data Design Highlights

Data ownership names the boundary responsible for truth. Denormalization and read models help query paths but add staleness and synchronization concerns. Dual writes are risky because one write can succeed while the other fails.

## Failure Analysis Highlights

A timeout does not prove whether the downstream action happened. Backlogs require checking poison messages, slow consumers, dependency latency, and partition hot spots. Circuit breakers protect callers and downstream dependencies from repeated failing calls.

## Migration Strategy Highlights

Expand-and-contract means adding compatible shape first, writing and reading both where needed, backfilling, switching reads, and removing old shape later. Rollback can be limited by destructive schema changes, external side effects, and consumed event versions.

## Java Code Reading Highlights

The snippets resemble idempotency deduplication, bulkhead capacity isolation, and a circuit breaker state transition. In-memory examples are not durable and are not suitable for distributed production use.

## Interview Highlights

Start with requirements and assumptions, speak in tradeoffs, avoid claiming one architecture is always best, and close by summarizing the chosen design, risks, and revisit triggers.
