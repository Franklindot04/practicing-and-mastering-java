# Testing Strategy Foundations

Testing is how a team gathers evidence that software behaves well enough for the risks it carries. It is not a ritual for producing a perfect score. A useful testing strategy balances confidence, feedback speed, realism, isolation, and maintenance cost.

This section connects advanced system design work to quality engineering. A design is only trustworthy when its important assumptions can be checked through tests, reviews, observability, and release evidence.

## Study Order

1. [Why Testing Exists](why-testing-exists.md)
2. [Testing Levels And Tradeoffs](testing-levels-and-tradeoffs.md)
3. [Test Strategy Checklist](test-strategy-checklist.md)

## Core Ideas

| Idea | Practical meaning |
| --- | --- |
| Verification | Are we building the thing according to the specification? |
| Validation | Are we building the right thing for the user or business need? |
| Defect | A flaw in code, design, data, requirements, or operations. |
| Failure | The visible incorrect behavior caused by a defect under certain conditions. |
| Root cause | The deeper reason the defect escaped, such as unclear requirements or missing feedback. |
| Risk reduction | Testing focuses attention where failure would hurt most. |
| Feedback speed | Fast tests help developers learn while context is still fresh. |
| Realism | Tests that use realistic boundaries can reveal integration and environment problems. |
| Maintainability | A test suite that is hard to update eventually stops protecting the code. |

## A Small Java Example

Imagine an order total rule:

```java
BigDecimal total(Order order) {
    if (order.items().isEmpty()) {
        throw new IllegalArgumentException("order must contain at least one item");
    }
    return order.items().stream()
            .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
}
```

Good test strategy asks several questions:

- Unit scope: does `total` handle one item, many items, zero quantity rules, and empty orders?
- Boundary scope: what is the smallest and largest accepted quantity?
- Negative path: what happens when the order is invalid?
- Integration scope: does the service pass the correct calculated total to persistence or payment boundaries?
- Acceptance scope: does the user journey show the correct final total before checkout?

Each test level gives different evidence. None of them proves the whole product is correct.

## Testing Pyramid And Testing Trophy

The testing pyramid emphasizes many fast unit tests, fewer integration tests, and a small number of end-to-end tests. The testing trophy emphasizes integration tests more heavily when confidence depends on how units collaborate. Both are thinking tools, not laws.

| Model | Strength | Risk if applied blindly |
| --- | --- | --- |
| Testing pyramid | Encourages fast, focused feedback and avoids excessive end-to-end suites. | Can under-test important integration behavior if teams treat unit tests as sufficient. |
| Testing trophy | Encourages realistic collaboration tests around valuable behavior. | Can become slow or vague if test boundaries are not controlled. |
| Risk-based model | Prioritizes tests where failure impact and defect likelihood are highest. | Can miss low-probability defects if risk analysis is rushed. |

## Test Level Comparison

| Level | Typical boundary | Confidence gained | Common cost |
| --- | --- | --- | --- |
| Unit | One class or function with dependencies replaced or simplified. | Logic correctness and edge cases. | Can become coupled to implementation details. |
| Component | A meaningful slice, such as a service with in-memory collaborators. | Collaboration inside a module. | Requires careful fixture and state management. |
| Integration | Real interaction between modules, database adapters, HTTP clients, or messaging adapters. | Boundary behavior and configuration assumptions. | Slower setup and harder diagnostics. |
| Contract | Agreement between a consumer and provider, often schema or API behavior. | Compatibility without running every service together. | Contracts need ownership and versioning discipline. |
| System | A complete application or subsystem. | Broad behavior across layers. | More moving parts and slower feedback. |
| End-to-end | A user journey through the deployed or deployable system. | High-value workflow confidence. | Fragility, environment drift, and expensive failures. |

## Functional And Non-Functional Testing

Functional tests ask whether the software does the intended behavior. Non-functional tests ask whether it does that behavior with acceptable qualities, such as performance, accessibility, security, reliability, and operability.

Examples:

- Functional: calculating the correct order total.
- Performance concept: calculating totals quickly enough for expected traffic.
- Security concept: preventing unauthorized users from seeing another user's order.
- Reliability concept: retrying a transient notification failure without double-charging.
- Accessibility concept: checkout errors are announced clearly to screen-reader users.

## Black-Box, White-Box, And Gray-Box Testing

| Style | What the tester knows | Useful for |
| --- | --- | --- |
| Black-box | Inputs, outputs, and visible behavior. | Acceptance criteria, API behavior, user workflows. |
| White-box | Internal code and branches. | Unit tests, branch coverage, failure injection. |
| Gray-box | Some internals, some external behavior. | Component tests and integration boundary tests. |

## Deterministic And Probabilistic Behavior

Most automated tests should be deterministic: the same code and same inputs should produce the same result. Some behavior is inherently probabilistic or timing-sensitive, such as randomized algorithms or concurrent scheduling. In those cases, tests should control seeds, set bounded waits, assert invariants, and avoid relying on luck.

## Practical Strategy Checklist

- Identify the highest-risk behavior first.
- Define the system under test and the boundary around it.
- Decide which checks need speed and which need realism.
- Test happy paths, edge cases, negative paths, and failure scenarios.
- Keep most tests deterministic and independent.
- Use manual exploratory testing where human judgment matters.
- Avoid automating flows whose expected behavior is still changing daily.
- Treat coverage as a signal about exercised code, not proof of correctness.
- Review failures for root causes, not only broken assertions.
- Revisit the strategy when architecture, risk, or team ownership changes.

## Common Mistakes

- Counting tests instead of asking what risk they reduce.
- Treating 100% coverage as a correctness guarantee.
- Automating unstable workflows too early.
- Writing end-to-end tests for every small branch of logic.
- Having only unit tests for behavior that mostly fails at boundaries.
- Using arbitrary sleeps for asynchronous behavior.
- Ignoring test maintenance cost during design.
- Keeping flaky tests in the main signal without investigation.

## Review Questions

1. What is the difference between verification and validation?
2. Why can a test suite with high coverage still miss important defects?
3. When might an integration test give more useful evidence than a unit test?
4. What makes an end-to-end test valuable enough to justify its cost?
5. How would you decide whether a workflow should be automated or tested manually?

