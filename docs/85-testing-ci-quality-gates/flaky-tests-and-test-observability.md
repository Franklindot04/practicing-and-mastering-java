# Flaky Tests And Test Observability

Flaky tests damage trust because the same code can produce different outcomes. Retrying a flaky test may reduce pipeline disruption, but it can also hide a real reliability signal.

## Detection

Look for:

- Failure without relevant code change.
- Failures that cluster on specific CI workers.
- Failures that disappear when run alone.
- Timing-sensitive assertions.
- Tests affected by parallel execution.
- Environment drift or shared data.

## Quarantine Workflow

Quarantine can be useful when a flaky test blocks unrelated work, but it carries risk. A responsible quarantine includes:

- Owner.
- Reason.
- Link to failure evidence.
- Deadline.
- Replacement coverage or explicit risk acceptance.
- Review before release gates.

## Failure Diagnostics

Useful diagnostics include:

- Clear assertion messages.
- Relevant input data and random seeds.
- Logs with correlation identifiers.
- Test duration and retry count.
- Environment metadata.
- Artifacts for generated reports.

## Trend Monitoring

CI should show whether quality is improving or degrading:

- Flaky test rate.
- Time to fix broken builds.
- Test duration trends.
- Coverage trend for important areas.
- Static-analysis trend.
- Escaped defect trend.

Metrics should trigger better conversations, not scoreboard behavior.

