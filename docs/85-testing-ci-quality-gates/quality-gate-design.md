# Quality Gate Design

A quality gate is a policy decision based on evidence. It should be strict enough to protect important behavior and flexible enough to avoid blocking on low-value noise.

## Required Versus Advisory Checks

| Check | Often required? | Notes |
| --- | --- | --- |
| Compile | Yes | Broken compilation blocks learning and review. |
| Focused unit tests | Yes | Fast and high signal. |
| Relevant component tests | Yes | Useful for behavior changes. |
| Coverage threshold | Maybe | Better as a trend unless tied to critical areas. |
| Mutation report | Maybe | Strong signal for critical logic, but can be slower. |
| Static-analysis warning | Depends | New severe warnings may block; broad legacy warnings may be advisory. |
| End-to-end suite | Depends | Valuable for release gates, often too expensive for every small change. |

## Reports And Artifacts

CI should preserve enough evidence to diagnose failures:

- Test reports.
- Coverage reports.
- Mutation reports.
- Static-analysis reports.
- Logs.
- Screenshots or relevant artifacts for UI or end-to-end tests.
- Build metadata such as Java version and commit hash.

Reports are useful only if someone reviews trends and owns failures.

## Branch Protection Concepts

Branch protection can require selected checks before merge. Choose checks that are stable, actionable, and relevant to the branch. Required flaky checks train teams to ignore CI, while missing required checks allow regressions to merge.

## Release Gates

Release gates may include:

- Required CI checks passed.
- Known defects triaged.
- Rollback confidence reviewed.
- Post-deployment validation planned.
- Smoke tests defined.
- Canary validation concepts considered for higher-risk changes.
- Monitoring or alerting on quality degradation.

Release confidence is evidence-based. It should not claim zero risk.

