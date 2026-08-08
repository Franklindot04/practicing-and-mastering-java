# Incident Runbook

State the symptom, collect bounded evidence, compare baseline and candidate behavior, identify the likely bottleneck, choose the smallest reversible change, and verify with another bounded run.

## Triage Flow

1. State the user-visible or learner-visible symptom.
2. Confirm the workload configuration is within safe bounds.
3. Choose one primary evidence type: latency, CPU, allocation, thread state, or GC.
4. Collect a bounded sample.
5. Compare against baseline or expected behaviour.
6. Decide whether to collect more evidence, change code, rollback the candidate, or document a limitation.

## Escalation Criteria

Escalate the investigation when evidence suggests unbounded memory growth, uncontrolled thread creation, repeated lock contention, large regression beyond budget, sensitive data exposure, or diagnostic tooling that requires permissions outside normal local practice.

## Operational Implications

In production, the same flow would include incident severity, customer impact, dashboards, logs, traces, owner assignment, rollback authority, and post-incident review. The workbench keeps the flow local so it can be practiced safely.

## Verification

A fix is not verified by a single profiler screenshot. Re-run the bounded workload, compare the same metric, and record what changed and what remains uncertain.
