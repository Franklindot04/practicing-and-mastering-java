# Java Testing Patterns

This standalone Maven project demonstrates practical testing patterns with plain Java and JUnit 5. It avoids external infrastructure and mocking libraries so the examples show the design choices directly.

## What It Demonstrates

- Arrange-act-assert and descriptive test names.
- Parameterized tests and exception assertions.
- Test fixtures and test data builders.
- Fake repositories, stub discounts, and spy-like recording notifications.
- `Clock` and deterministic ID generation.
- Retry policy testing.
- State-based and behavior-based verification.
- Boundary-value and equivalence-partitioning tests.
- Property-style invariants with deterministic generated inputs.
- Concurrency testing with bounded synchronization.
- Eventual-result polling without arbitrary long sleeps.

## Mutation Testing Discussion

Mutation testing would intentionally change small pieces of production code, such as replacing `>` with `>=` or removing a validation branch. If the tests still pass, the mutant survived and the suite may be missing an assertion or scenario. A surviving mutant is a signal for review, not automatic proof that the suite is bad.

For this project, useful mutations would include:

- Allowing zero quantity.
- Removing the preferred-customer discount.
- Returning the same generated ID every time.
- Retrying one fewer or one extra time.

## Flaky Test Anti-Example

A bad test for asynchronous behavior might submit work, sleep for five seconds, and then assert the result. That test is slow when the result is ready immediately and still flaky when the machine is busy. The committed tests use bounded polling and synchronization primitives instead.

## Run

```bash
mvn test
```

