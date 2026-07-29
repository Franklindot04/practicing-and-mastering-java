# Alerting, SLOs, And Incident Diagnostics

Alerts should call attention to actionable risk or user-visible impact. They are not a place to mirror every metric, log line, or internal cause candidate.

## Study Order

1. [Alert Design And Noise Control](alert-design-and-noise-control.md)
2. [SLIs, SLOs, Error Budgets, And Burn Rates](slis-slos-error-budgets-and-burn-rates.md)
3. [Incident Diagnostics And Learning](incident-diagnostics-and-learning.md)

## Alert Purpose

| Signal | Better destination |
| --- | --- |
| User-visible outage or fast error-budget burn | Page. |
| Degraded but non-urgent condition | Ticket or next-business-day review. |
| Interesting internal trend | Dashboard or investigation note. |
| Known maintenance impact | Suppressed or routed with context. |

Alert on symptoms when possible, then use cause-oriented evidence to diagnose. A saturated queue may be worth alerting on when it predicts user impact, but paging on every transient internal spike creates alert fatigue.

## Review Questions

1. What makes an alert actionable?
2. Why are symptoms and causes handled differently?
3. How do SLIs, SLOs, and SLAs differ?
4. Why does error-budget burn rate matter?
5. What evidence should be preserved before restarting a Java service?

