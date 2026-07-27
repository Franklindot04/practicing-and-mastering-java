# Isolation And Determinism

A deterministic test has the same result for the same code and inputs. An isolated test does not depend on state left by another test or by the local machine.

## Common Isolation Risks

| Risk | Safer design |
| --- | --- |
| Global mutable state | Reset it explicitly or avoid it in domain code. |
| Current time | Inject `Clock` and use `Clock.fixed` in tests. |
| Random values | Seed `Random` or inject a deterministic generator. |
| File-system state | Use temporary directories and clean them after the test. |
| Environment variables | Pass configuration as values where possible. |
| Database rows | Use rollback, isolated schemas, or ephemeral databases. |
| Test ordering | Make each test create its own required state. |

## Clock Abstraction

```java
final class SubscriptionService {
    private final Clock clock;

    SubscriptionService(Clock clock) {
        this.clock = clock;
    }

    boolean isExpired(Instant expiresAt) {
        return !expiresAt.isAfter(Instant.now(clock));
    }
}
```

```java
@Test
void reportsSubscriptionExpiredAtCurrentInstant() {
    Clock fixedClock = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    SubscriptionService service = new SubscriptionService(fixedClock);

    assertTrue(service.isExpired(Instant.parse("2026-01-01T00:00:00Z")));
}
```

## Seeded Randomness

Random data can discover cases a hand-written list misses, but the test should be repeatable:

```java
Random random = new Random(42);
for (int i = 0; i < 100; i++) {
    int quantity = random.nextInt(1, 100);
    assertTrue(policy.canReserve(quantity), "quantity=" + quantity);
}
```

The failure message should include enough data to reproduce the case.

## Avoid Arbitrary Sleeps

Prefer bounded polling:

```java
Instant deadline = Instant.now().plusMillis(500);
while (Instant.now().isBefore(deadline) && !repository.exists("order-1")) {
    Thread.sleep(10);
}
assertTrue(repository.exists("order-1"));
```

This still uses a small sleep between checks, but the test waits for a condition and has a clear upper bound.

## False Positives And False Negatives

A false positive test fails even though the product behavior is acceptable. A false negative test passes even though the product behavior is wrong. Both weaken trust, so diagnosis should ask whether the test, implementation, or requirement is wrong.

