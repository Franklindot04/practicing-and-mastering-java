# Testing And Quality Engineering Exercises

## 1. Choose Test Levels

- Difficulty: beginner
- Concepts: unit, component, integration, end-to-end
- Scenario: An order service calculates totals, reserves inventory, and sends a notification.
- Requirements: Propose tests for calculation, reservation, notification, and the main user journey.
- Constraints: No live external services.
- Questions: Which behavior belongs at which test level? What should not be end-to-end?
- Hints: Separate business rules from boundaries.
- Stretch challenge: Add one failure-path test for each level.

## 2. Design A Testing Pyramid Or Trophy

- Difficulty: beginner
- Concepts: feedback speed, realism, maintenance
- Scenario: A small Java API has many domain rules and a few important workflows.
- Requirements: Sketch a test mix.
- Constraints: CI should finish quickly.
- Questions: Where do you place most checks? What evidence is missing?
- Hints: Use risk, not fashion, to choose the shape.
- Stretch challenge: Explain when you would change the mix.

## 3. Test-Case Design

- Difficulty: beginner
- Concepts: happy path, edge case, negative path
- Scenario: Quantity must be from 1 through 99.
- Requirements: Write at least six test cases.
- Constraints: Avoid redundant examples.
- Questions: Which cases are boundaries? Which are invalid?
- Hints: Test just below, at, and just above boundaries.
- Stretch challenge: Add equivalence partitions.

## 4. Test-Double Selection

- Difficulty: intermediate
- Concepts: dummy, stub, spy, mock, fake
- Scenario: A service reads tax rates, writes orders, and emits a notification.
- Requirements: Choose a double for each collaborator.
- Constraints: Avoid mocking everything.
- Questions: Where is state verification enough? Where is behavior verification useful?
- Hints: Prefer fakes for meaningful state.
- Stretch challenge: Identify one contract risk introduced by a fake.

## 5. Improve A Brittle Test

- Difficulty: intermediate
- Concepts: over-mocking, implementation coupling
- Scenario: A test verifies five internal method calls in exact order.
- Requirements: Rewrite the test around observable behavior.
- Constraints: Keep one meaningful interaction assertion.
- Questions: Which assertions were implementation details?
- Hints: Ask what a user or caller can observe.
- Stretch challenge: Explain what refactors should not break the test.

## 6. Deterministic Time And Randomness

- Difficulty: intermediate
- Concepts: `Clock`, seeded randomness
- Scenario: Coupons expire at midnight and IDs include a random suffix.
- Requirements: Design deterministic tests.
- Constraints: No sleeps and no unseeded randomness.
- Questions: Which dependencies should be injected?
- Hints: Use fixed clocks and deterministic generators.
- Stretch challenge: Add one generated invariant test.

## 7. Retry, Idempotency, And Eventual Consistency

- Difficulty: intermediate
- Concepts: retry policy, idempotency, bounded polling
- Scenario: Duplicate order submissions can happen during retries.
- Requirements: Test that the same request is processed once.
- Constraints: Avoid arbitrary long sleeps.
- Questions: What state proves idempotency? What timeout is reasonable?
- Hints: Use request IDs and bounded waits.
- Stretch challenge: Add a failure case after max retries.

## 8. Contract And Database Integration Strategy

- Difficulty: advanced
- Concepts: contract tests, schema compatibility, database tests
- Scenario: A consumer depends on an order API and the provider changes response fields.
- Requirements: Propose contract and persistence tests.
- Constraints: No live production endpoints.
- Questions: Which changes are breaking? Which database behavior must be real?
- Hints: Adding optional fields differs from removing required fields.
- Stretch challenge: Add a migration rollback test idea.

## 9. Diagnose Flakiness

- Difficulty: advanced
- Concepts: isolation, CI diagnostics, environment drift
- Scenario: A test fails only in CI when tests run in parallel.
- Requirements: Create an investigation plan.
- Constraints: Do not disable the test without ownership.
- Questions: What data should be collected? What likely causes fit?
- Hints: Check shared state, files, ports, time, and ordering.
- Stretch challenge: Propose a quarantine policy.

## 10. Interpret Coverage And Mutation Results

- Difficulty: advanced
- Concepts: line coverage, branch coverage, mutation testing
- Scenario: A module has 95% line coverage but a mutant changing `>` to `>=` survives.
- Requirements: Explain the risk.
- Constraints: Do not claim coverage proves correctness.
- Questions: What test might be missing? When might the mutant be equivalent?
- Hints: Look for boundary tests.
- Stretch challenge: Recommend a quality gate for this module.

## 11. Property-Based And Concurrency Test Design

- Difficulty: advanced
- Concepts: invariants, generated inputs, synchronization
- Scenario: Inventory reservation must never make stock negative.
- Requirements: Design property-style and concurrency tests.
- Constraints: Results must be reproducible.
- Questions: What invariant matters? How do you coordinate threads?
- Hints: Use seeds, latches, and bounded waits.
- Stretch challenge: Explain what these tests still cannot prove.

## 12. CI Quality Gates And Release Readiness

- Difficulty: advanced
- Concepts: required checks, advisory checks, release confidence
- Scenario: A release candidate has passing tests, one quarantined flaky test, and lower branch coverage.
- Requirements: Make a release recommendation.
- Constraints: Avoid absolute claims.
- Questions: What blocks release? What needs explicit risk acceptance?
- Hints: Separate evidence from guarantees.
- Stretch challenge: Add post-release validation steps.

## 13. Defect Triage And Strategy Review

- Difficulty: advanced
- Concepts: severity, priority, root cause, escaped defects
- Scenario: A validation bug reached users despite many tests.
- Requirements: Triage the defect and update the testing strategy.
- Constraints: Do not simply add more tests everywhere.
- Questions: What failed in requirements, tests, review, or observability?
- Hints: Find the narrowest process improvement.
- Stretch challenge: Define a regression test and a prevention change.

