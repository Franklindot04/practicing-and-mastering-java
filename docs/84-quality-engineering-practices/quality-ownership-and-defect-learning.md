# Quality Ownership And Defect Learning

Quality is owned by the team, not by a single testing role. Developers, reviewers, product owners, and operators all shape how defects are prevented, detected, diagnosed, and learned from.

## Shift Left And Shift Right

Shift-left practices move feedback earlier:

- Clear requirements and acceptance criteria.
- Design review.
- Unit and component tests.
- Static analysis.
- Pair testing and exploratory testing during development.

Shift-right practices learn from real operation:

- Safe monitoring.
- Post-release smoke checks.
- Error trends.
- Performance and reliability observations.
- Escaped defect analysis.

Both matter. Earlier feedback is cheaper, while later feedback reveals reality that pre-release checks can miss.

## Defect Triage

| Term | Meaning |
| --- | --- |
| Severity | How bad the impact is. |
| Priority | How soon it should be fixed. |
| Escaped defect | A defect found after it should have been caught earlier. |
| Root cause | The system weakness that allowed the defect to exist or escape. |
| Quality debt | Deferred work that makes future quality harder or slower. |

High severity does not always mean highest priority, and low severity does not mean safe to ignore. Context matters.

## Useful Metrics And Vanity Metrics

Useful metrics support decisions:

- Defect escape trends by area.
- Flaky test rate.
- Time to diagnose failures.
- Coverage of high-risk behavior.
- Mutation survivors in critical logic.
- Review rework patterns.

Vanity metrics look impressive but do not guide action:

- Raw test count without risk context.
- Coverage percentage treated as a goal by itself.
- Defect count without severity or discovery phase.
- Green builds that ignore quarantined risk.

## Root-Cause Questions

1. Was the requirement ambiguous?
2. Was the design hard to test?
3. Did the test boundary miss the real failure mode?
4. Did observability make diagnosis too slow?
5. Did the review process focus on style while missing behavior?
6. What small change would make this class of defect less likely?

