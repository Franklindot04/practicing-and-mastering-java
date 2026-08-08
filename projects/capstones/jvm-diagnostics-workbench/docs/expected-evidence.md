# Expected Evidence

Useful evidence includes workload configuration, latency percentiles, thread state samples, allocation observations, regression ratios, and a written explanation of what the evidence can and cannot prove.

## Evidence Checklist

- Symptom: what changed or failed.
- Workload: name, bounds, and input shape.
- Environment: Java version, Maven version, operating system, and relevant JVM flags.
- Measurement: latency percentiles, completion result, allocation observation, thread-state pattern, or GC signal.
- Comparison: baseline versus candidate when evaluating regression.
- Limitation: what the evidence cannot prove.
- Decision: rollback, investigate more, change code, or accept.

## Correlation

Stronger conclusions usually connect multiple signals. For example, a latency regression is more credible when p95 worsens, thread dumps show repeated contention, and code review identifies a new shared lock. One signal can start an investigation, but it rarely ends it.

## Quality Bar

Evidence should be reproducible enough that another learner can rerun the scenario. Avoid vague statements such as "it got slower" without workload size, metric, threshold, and result.
