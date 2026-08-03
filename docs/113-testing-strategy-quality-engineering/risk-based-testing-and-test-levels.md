# Risk-Based Testing And Test Levels

Testing strategy starts with risk, not tooling. In a Java system, the riskiest paths are usually where money moves, security decisions happen, data is persisted, messages cross service boundaries, concurrency changes state, or failure handling protects users from partial work. A low-risk formatter method and a payment authorization path should not receive the same testing investment simply because both can be covered by unit tests.

## Risk Model

Assess each behavior by likelihood and severity. Likelihood asks how easily the behavior can break: complex branching, concurrency, serialization, persistence mapping, and external integration all raise likelihood. Severity asks what happens if it breaks: data loss, incorrect billing, security exposure, outage, legal exposure, or confusing user behavior all raise severity. Critical paths with high severity deserve multiple forms of evidence.

Risk-based prioritization should include:

- business criticality
- technical complexity
- frequency of change
- blast radius
- reversibility
- observability in production
- historical defect patterns
- regulatory or contractual expectations

## Test Levels

Unit tests check small decisions quickly. They are best for pure functions, validation, branching rules, boundary values, and error behavior. They should be deterministic, isolated, and cheap enough to run constantly.

Component tests exercise a meaningful slice of code inside one process. In Java applications this might include a service, mapper, repository fake, validator, and metrics collector. They prove collaboration without requiring a network or real infrastructure.

Integration tests verify real boundaries: SQL queries against a real database, HTTP serialization, message schemas, file permissions, configuration wiring, transaction behavior, and framework annotations. They should be fewer than unit tests because they are slower and harder to diagnose, but they provide evidence unit tests cannot.

Contract tests protect boundary compatibility. Consumer-driven contracts capture what a client needs; provider contracts prove the producer still satisfies that contract. They are especially useful for REST APIs, events, schemas, and messaging systems where teams evolve independently.

End-to-end tests prove a complete user or system workflow across deployed components. They are valuable for smoke coverage and critical acceptance paths, but expensive and often brittle. Keep them focused.

Acceptance tests describe externally visible behavior in business language. They may run at several technical levels. The important property is that product owners, engineers, and testers can agree what they prove.

Smoke tests answer whether a build or environment is alive enough for deeper testing. Regression tests protect previously broken behavior. Exploratory testing searches for unknown risks that scripted tests missed. Production verification checks health, telemetry, feature flags, migrations, and rollback signals after release.

## Pyramid, Trophy, And Trade-Offs

The test pyramid emphasizes many fast unit tests, fewer integration tests, and very few end-to-end tests. The testing trophy emphasizes integration and confidence around realistic user behavior. Both models are reminders, not laws. The right shape depends on the architecture, risk profile, and cost of feedback.

A strong Java strategy balances:

- confidence versus cost
- feedback speed versus realism
- isolation versus integration evidence
- defect localization versus production resemblance
- maintenance effort versus risk reduction

## Ownership

Every important test needs an owner. Ownership includes fixing failures, keeping fixtures readable, trimming obsolete tests, and improving diagnostics. A slow and flaky suite with no owner trains developers to ignore it.

## Practical Checklist

For each feature, ask:

1. What can go wrong?
2. Who is harmed if it goes wrong?
3. Which test level gives the cheapest useful evidence?
4. Which boundary needs a real integration check?
5. Which failure mode needs a deliberate test?
6. Which signals will help diagnose a failure?
7. What evidence is still missing after tests pass?

Passing tests reduce uncertainty. They do not prove production readiness.
