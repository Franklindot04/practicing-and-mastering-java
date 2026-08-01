# Physical Time Clock Skew And Ordering

Physical clocks are useful for logs, expiry, user-visible timestamps, and audits. They are dangerous when treated as proof of causality.

## Wall-Clock Time

`Instant.now()` returns a wall-clock timestamp. Wall clocks can move forward or backward because of synchronization, leap seconds, virtualization, manual changes, or host drift.

```java
Instant observedAt = Instant.now(clock);
```

Injecting `Clock` makes time testable, but it does not remove skew between nodes.

## Monotonic Time

Elapsed durations should use monotonic time when possible because a monotonic source does not move backward within the process.

```java
long started = System.nanoTime();
Duration elapsed = Duration.ofNanos(System.nanoTime() - started);
```

`System.nanoTime()` is for elapsed time, not timestamps that can be compared across JVMs.

## Clock Skew And Drift

Clock skew means two nodes show different times at the same moment. Clock drift means a clock gains or loses time at a different rate.

Failure examples:

- a lease appears expired on one node but valid on another
- a "latest update wins" rule loses the real latest write because one host clock is ahead
- logs appear out of order across services
- token expiry checks disagree between issuer and verifier

## Timestamps And Ordering

Timestamps can order observations made by one clock. They cannot prove causal order across independent nodes. If event B has a later timestamp than event A, B may still have happened without seeing A.

## Review Questions

1. Why should elapsed time not rely on wall-clock movement?
2. How can clock skew break a lease?
3. Why do timestamps alone not prove causality?
