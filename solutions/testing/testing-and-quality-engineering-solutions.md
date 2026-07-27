# Testing And Quality Engineering Solutions

## 1. Choose Test Levels

Calculation rules fit unit tests because they are fast and deterministic. Reservation orchestration fits component tests with an in-memory repository. Notification can use a recording fake to verify the useful interaction. A small end-to-end smoke test can cover the main journey, but every calculation edge case should not be pushed to end-to-end.

## 2. Design A Testing Pyramid Or Trophy

A reasonable mix is many unit tests for domain rules, several component tests for service collaboration, a few integration or contract tests around persistence/API boundaries, and a tiny end-to-end smoke suite. If most defects appear at boundaries, increase component and integration coverage. If CI becomes slow, split required and scheduled checks.

## 3. Test-Case Design

Useful cases include `0`, `1`, `2`, `98`, `99`, and `100`. Values `1` through `99` are valid. Values below `1` and above `99` are invalid partitions. The boundary cases are `0/1` and `99/100`; they carry more risk than arbitrary middle values.

## 4. Test-Double Selection

Use a stub for tax rates when the rate itself is not under test. Use a fake repository when state matters. Use a spy-like recording notifier when sending the notification is part of behavior. A mock can be appropriate for a payment gateway interaction, but verifying every internal call would over-couple the test.

## 5. Improve A Brittle Test

Replace ordered internal-call verification with assertions on the final order state and one meaningful notification or payment interaction. The rewritten test should survive refactors such as extracting helper methods, changing private method order, or replacing an internal collection.

## 6. Deterministic Time And Randomness

Inject `Clock` for expiration logic and use `Clock.fixed` in tests. Inject an ID generator or seeded random source for suffixes. A generated invariant could assert that valid coupon durations never expire before their start time. Record the seed on failure.

## 7. Retry, Idempotency, And Eventual Consistency

Persist request IDs and return the same reservation for duplicate requests. Assert that stock decreases once and only one notification is recorded. Retry tests should count attempts deterministically. Eventual consistency should use bounded polling for the expected state rather than a fixed long sleep.

## 8. Contract And Database Integration Strategy

Removing a required response field or adding a required request field is breaking. Adding an optional response field is usually compatible. Contract tests should capture consumer-required fields and supported status values. Database integration tests should use a controlled database or schema to verify mappings, constraints, transactions, and migrations.

## 9. Diagnose Flakiness

Collect failure logs, test order, worker ID, random seeds, timing, and environment metadata. Likely causes include shared mutable state, shared files, port collisions, database rows, or clock assumptions. Quarantine only with an owner, reason, deadline, and risk note.

## 10. Interpret Coverage And Mutation Results

High line coverage says code ran, not that behavior was asserted. A surviving `>` to `>=` mutant suggests a missing boundary test around equality. It may be equivalent only if another rule prevents equality from mattering. A quality gate could require mutation review for critical pricing or authorization logic.

## 11. Property-Based And Concurrency Test Design

The invariant is that stock never becomes negative and successful reservations never exceed initial stock. Use seeded generated quantities for reproducibility. For concurrency, use latches to start workers together and a timeout to avoid hanging. These tests still sample schedules and inputs, so they do not prove every interleaving is safe.

## 12. CI Quality Gates And Release Readiness

Passing tests are positive evidence, not certification. A quarantined flaky test needs owner, scope, and release risk. Lower branch coverage may be advisory unless it affects high-risk behavior. Release can proceed only if blockers are resolved or residual risks are explicitly accepted with post-release validation.

## 13. Defect Triage And Strategy Review

Severity describes user impact; priority describes fix urgency. Root-cause analysis should ask whether requirements omitted the validation rule, tests missed the boundary, review missed the behavior, or observability delayed diagnosis. Add a focused regression test and improve acceptance criteria or review checklist for similar validation rules.

