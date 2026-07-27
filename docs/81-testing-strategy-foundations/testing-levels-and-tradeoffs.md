# Testing Levels And Tradeoffs

A test level is a choice about boundaries. The same behavior can be tested at multiple levels, but duplicating every assertion everywhere creates slow feedback and expensive maintenance.

## Scope, Boundary, And Confidence

The scope is the behavior under inspection. The boundary is where the test stops and replaces or observes the outside world. Confidence is the evidence the test gives you about a risk.

For example, a checkout service might have:

- A unit test for discount calculation.
- A component test for checkout orchestration with fake inventory and notification ports.
- A contract test for the payment request shape.
- An end-to-end smoke test for the main checkout journey.

These tests overlap, but they do not answer the same question.

## Speed, Realism, Isolation, Maintainability

| Choice | Benefit | Tradeoff |
| --- | --- | --- |
| More isolation | Fast, precise failures. | Can miss wiring, serialization, persistence, and environment defects. |
| More realism | Better evidence about deployed behavior. | Slower feedback and harder root-cause analysis. |
| More test data | More scenarios covered. | More fixture maintenance and more ways for tests to become unclear. |
| More automation | Repeatable checks. | Bad automation can fossilize weak requirements or unstable workflows. |
| More manual exploration | Human judgment and discovery. | Less repeatable and harder to scale. |

## Testing Types In Practice

| Type | Example | Good question |
| --- | --- | --- |
| Regression test | A fixed test for a bug that already escaped. | Will this specific failure stay fixed? |
| Smoke test | A shallow check that the main path starts and basic behavior works. | Is this build worth deeper testing? |
| Sanity test | A narrow check after a focused change. | Did the intended area still behave sensibly? |
| Acceptance test | A check against business acceptance criteria. | Does this satisfy the agreed behavior? |
| Exploratory test | Time-boxed investigation by a person. | What risks did our scripted tests miss? |

## Risk-Based Testing

Risk-based testing starts with impact and likelihood:

| Risk | Useful tests |
| --- | --- |
| Incorrect money calculation | Unit tests for math rules, boundary tests, property-style invariants, review of rounding policy. |
| Double submission | Idempotency unit/component tests, concurrency tests, integration checks around persistence. |
| API schema break | Contract tests, compatibility checks, consumer examples. |
| Slow checkout | Performance test concepts, profiling, synthetic load in a safe environment. |
| Poor failure diagnosis | Observability checks, structured logs, error-path tests. |

## When Not To Automate

Automation may be the wrong first move when:

- The requirement is still being discovered.
- A human must judge visual quality, wording, or usability.
- The setup would cost more than the risk justifies.
- The test would rely on live production services or credentials.
- A simpler unit or component test gives enough confidence.

Manual testing is not inferior by default. It is a different tool with different costs.

## A Strategy Sketch

```text
High-risk business rules      -> unit and component tests
Module collaboration          -> component tests
API compatibility             -> contract tests
Persistence and migrations    -> integration tests in controlled environments
Critical user journeys        -> a small end-to-end smoke suite
Operability and diagnosis     -> observability and failure-path checks
Release confidence            -> quality gates plus human review
```

