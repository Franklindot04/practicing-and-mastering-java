# Alert Design And Noise Control

Good alerts have an owner, severity, routing rule, short explanation, likely impact, and first diagnostic steps. They should say what changed and why a human is needed.

## Alert Severity

| Severity | Response |
| --- | --- |
| Paging | Immediate human response because users or critical workflows are at risk. |
| Ticketing | Planned response for degradation, cleanup, or follow-up. |
| Informational | No interruption; useful for dashboards, reviews, or context. |

## Noise Controls

- Deduplicate repeated alerts for the same service and symptom.
- Group related alerts under one incident when they share impact.
- Inhibit downstream alerts when an upstream dependency is already known down.
- Suppress planned maintenance windows.
- Define missing-data behavior deliberately.
- Remove stale alerts that no longer map to action.

Threshold alerts are simple and readable. Rate-of-change alerts catch rapid shifts. Composite alerts combine multiple signals. Anomaly detection can help, but it must still produce understandable, actionable output.

## Alert Review Checklist

- [ ] Does the alert map to user-visible impact or a leading indicator with strong evidence?
- [ ] Is there a clear owner and escalation path?
- [ ] Is the severity proportional?
- [ ] Is the runbook linked or described?
- [ ] Are duplicate and downstream symptoms grouped?
- [ ] Is missing data treated intentionally?
- [ ] Would this alert have caught a real incident or prevented one?
- [ ] Is there a clear reason not to make it a dashboard panel instead?

