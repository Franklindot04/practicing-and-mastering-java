# Quality Engineering Practices

Quality engineering is broader than running tests. It includes prevention, review, testability, observability, defect learning, release gates, and the discipline to improve how defects are found and prevented.

## Study Order

1. [Quality Ownership And Defect Learning](quality-ownership-and-defect-learning.md)
2. [Coverage, Mutation, And Generative Testing](coverage-mutation-and-generative-testing.md)
3. [Static Analysis, Reliability, And Release Readiness](static-analysis-reliability-release-readiness.md)

## Prevention And Detection

| Practice | Prevention or detection | Example |
| --- | --- | --- |
| Clear acceptance criteria | Prevention | Avoids building ambiguous behavior. |
| Code review | Both | Finds design and readability issues before merge. |
| Unit tests | Detection | Reveals broken local logic. |
| Static analysis | Detection | Flags unreachable code or unsafe patterns. |
| Observability | Detection and diagnosis | Helps explain failures after release. |
| Root-cause analysis | Prevention | Changes process so similar defects escape less often. |

## Signals, Not Guarantees

Coverage, pass rate, defect counts, mutation score, static-analysis warnings, and release checklists are signals. They help a team reason about risk. They do not guarantee correctness or production readiness.

## Quality Engineering Checklist

- Requirements are testable.
- Acceptance criteria describe success and important failure paths.
- Tests cover valuable behavior at appropriate boundaries.
- Observability supports diagnosis without exposing secrets.
- Static-analysis and compiler warnings are reviewed.
- Flaky tests are investigated rather than normalized.
- Defects are triaged by severity, priority, and root cause.
- Release readiness is based on evidence and known residual risk.

