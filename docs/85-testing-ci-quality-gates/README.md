# Testing In CI And Quality Gates

Continuous integration turns testing into shared feedback. A quality gate decides which signals should block a change, which should warn, and which should start follow-up work.

This repository already contains GitHub Actions workflow files, so this section focuses on strategy rather than replacing live CI configuration.

## Study Order

1. [CI Feedback Strategy](ci-feedback-strategy.md)
2. [Quality Gate Design](quality-gate-design.md)
3. [Flaky Tests And Test Observability](flaky-tests-and-test-observability.md)

## CI Layers

| Layer | Purpose |
| --- | --- |
| Local validation | Fast checks before pushing. |
| Pre-commit checks | Formatting, focused unit tests, or lightweight static checks. |
| Pull-request checks | Shared confidence before review and merge. |
| Merge checks | Required evidence for the target branch. |
| Main-branch validation | Confirms the integrated state still works. |
| Scheduled tests | Finds slower, broader, or environment-sensitive failures. |
| Release-candidate tests | Adds focused evidence before a release decision. |

## Core Warning

CI should increase confidence without pretending that every green build is production certification. A green pipeline means the configured checks passed for the sampled scenarios in that environment.

