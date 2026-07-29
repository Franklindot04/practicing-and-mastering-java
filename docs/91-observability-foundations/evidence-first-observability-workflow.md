# Evidence-First Observability Workflow

An evidence-first workflow keeps diagnosis grounded. It separates what is known, what is suspected, and what still needs proof.

## Investigation Flow

```text
Symptom reported
  -> define user-visible impact
  -> preserve recent evidence
  -> compare expected and actual behavior
  -> form a narrow hypothesis
  -> test with logs, metrics, traces, profiles, or dumps
  -> mitigate if impact is ongoing
  -> document gaps and follow-up work
```

## Before Instrumenting

Ask:

1. What operational question should this evidence answer?
2. Who will use it during an incident?
3. Is the signal a symptom, a cause candidate, or context?
4. Which fields are safe and stable?
5. Could any label explode cardinality?
6. What data volume will this create?
7. How will this evidence be removed or changed later?

## Common Mistakes

| Mistake | Why it hurts |
| --- | --- |
| Logging prose without fields | Hard to query and correlate. |
| Alerting on every internal cause | Creates noisy pages and hides user impact. |
| Using request IDs as metric labels | Creates high-cardinality metrics. |
| Dropping context at executor boundaries | Makes asynchronous failures hard to connect. |
| Treating dashboards as proof | Dashboards summarize evidence; they do not replace investigation. |
| Capturing dumps casually | Dumps can contain sensitive memory and must be handled carefully. |

## Scenario: Slow Order Processing

The symptom is that order confirmation is delayed. Evidence shows request traffic is normal, errors are low, queue depth is rising, worker active count is maxed, dependency latency p95 is high, and retry count rose after a configuration change.

Reasonable conclusion: dependency slowness and retries are contributing to queue saturation. That is stronger than saying "the dependency is the root cause" because the evidence may still miss worker lock contention, garbage collection pauses, or a recent code path change.

## Review Questions

1. What evidence would you preserve before restarting a failing Java service?
2. Why should mitigation and diagnosis be tracked separately?
3. How can a dashboard hide an instrumentation gap?
4. What makes an observability checklist safer than "log everything"?
5. How would you explain uncertainty in an incident note?

