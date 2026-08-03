# Deterministic Unit And Component Testing

A deterministic test gives the same result for the same code, data, and environment. Determinism is a quality feature. It lets Java engineers trust failures, parallelize safely, and debug quickly.

## Test Shape

Arrange-Act-Assert and Given-When-Then are both useful because they separate setup, behavior, and verification. A test name should describe the behavior and condition, not the implementation detail. Prefer names such as `rejectsExpiredReservation` over `callsClockInstant`.

Good tests usually verify behavior. Implementation tests are useful only when the implementation is the contract, such as a cache eviction policy or a concurrency boundary. Private behavior should be tested indirectly through public outcomes. If private code is too complex to observe, that is often a design smell.

## Fixtures And Data

Use fixtures, builders, and factory methods to make intent visible. Builders should hide irrelevant defaults while making important values explicit. Avoid one giant shared fixture that makes every test depend on the same mutable state.

Parameterized tests are useful for equivalence classes and boundary values. Examples include empty input, one item, maximum allowed length, invalid enum value, repeated message id, expired timestamp, and exact threshold crossings.

## Control Non-Determinism

Inject `Clock` instead of calling `Instant.now()` inside business logic. Inject identifier generators instead of calling `UUID.randomUUID()` directly. Use seeded randomness when generated data is helpful, and print or document the seed when a failure must be reproduced.

Avoid `Thread.sleep` as synchronization. Use latches, barriers, futures, virtual clocks, deterministic schedulers, or explicit polling with a short timeout and diagnostics. A test that merely waits and hopes is a future flaky test.

## Isolation

Tests should not leak state through static fields, singletons, environment variables, working directories, thread pools, system properties, random seeds, or current locale. If a test must change global state, it should restore it in teardown and avoid parallel execution.

## Assertions

Assertions should prove the behavior that matters. A weak assertion such as `assertNotNull(result)` may let a broken branch survive. Prefer assertions about values, state transitions, emitted events, errors, and observable side effects. Exception tests should verify both the exception type and the meaningful message or error code when those are part of the contract.

## Readability And Brittleness

A brittle test fails when harmless implementation details change. Overly specific interaction assertions, exact object construction order, or incidental timestamps can make refactoring expensive. Use interaction testing when the interaction is the behavior, such as an audit event, retry call, or no-call guarantee after validation fails. Use state-based assertions when final state is the contract.

## Component Tests

A component test can combine real domain services with in-memory fakes for repositories, clocks, id generators, and external adapters. This gives more confidence than a narrow unit test while staying fast and infrastructure-independent. Keep component tests observable: failures should reveal input, expected behavior, actual output, and relevant diagnostic state.
