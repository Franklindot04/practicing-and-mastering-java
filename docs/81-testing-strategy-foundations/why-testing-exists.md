# Why Testing Exists

Testing exists because software design contains assumptions. Tests make some of those assumptions executable, repeatable, and reviewable.

## Defects, Failures, And Root Causes

A defect is the underlying problem. A failure is what someone observes when the defect is triggered.

Example:

- Defect: the service assumes `quantity > 0` but never validates it.
- Failure: a customer gets a negative order total.
- Root cause: the acceptance criteria did not specify invalid quantities, and no boundary tests covered the rule.

Fixing only the code may stop one failure. Fixing the root cause improves the system that allowed the defect to escape.

## Verification Versus Validation

Verification asks, "Does the implementation match the specification?"

Validation asks, "Does the specification solve the real problem?"

A checkout form can be verified against every written requirement and still fail validation if users cannot understand the error messages.

## Confidence Is Evidence, Not Certainty

A passing test suite gives evidence. It cannot prove there are no defects because:

- Tests sample behavior; they do not cover every possible input and state.
- Requirements may be incomplete or misunderstood.
- Environment differences can change behavior.
- Concurrency, timing, and data interactions can expose rare failures.
- Tests themselves can contain bugs.

The goal is appropriate release confidence, not mathematical certainty for ordinary application work.

## Happy, Edge, Negative, And Failure Paths

| Path | Example |
| --- | --- |
| Happy path | A valid order with available inventory is reserved. |
| Edge case | The requested quantity equals the last item in stock. |
| Negative path | The requested quantity is zero or negative. |
| Failure scenario | The notification sender times out after reservation succeeds. |

Good strategies include all four, weighted by risk.

## Definition Of Done

A practical definition of done can include:

- Important behavior has tests at the right level.
- Known edge cases and failure paths are covered or consciously deferred.
- Manual exploratory testing was used where automation was weak.
- Logs and errors support diagnosis.
- The change has a rollback or mitigation plan when risk warrants it.
- Quality gates are passing or exceptions are documented.

Definition of done should improve release confidence without pretending that release risk is zero.

