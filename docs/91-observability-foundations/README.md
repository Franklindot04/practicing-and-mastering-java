# Observability Foundations

Observability is the ability to understand what a system is doing internally by studying the evidence it emits. Monitoring asks whether expected conditions are healthy. Observability helps investigate expected and unexpected behavior when the first question is still unclear.

## Study Order

1. [Observability Vocabulary And Models](observability-vocabulary-and-models.md)
2. [Telemetry Signals And Diagnostic Context](telemetry-signals-and-diagnostic-context.md)
3. [Evidence-First Observability Workflow](evidence-first-observability-workflow.md)

## Observability And Monitoring

| Topic | Monitoring | Observability |
| --- | --- | --- |
| Main question | Is a known condition healthy? | What is happening, and why might it be happening? |
| Typical inputs | Dashboards, alerts, checks. | Logs, metrics, traces, events, profiles, dumps, audit trails, domain signals. |
| Failure shape | Known knowns and known unknowns. | Unknown failure modes and new combinations of symptoms. |
| Risk | Missing new failure modes. | Collecting noisy or unsafe evidence without a question. |

Monitoring and observability support each other. A Java service may monitor request error rate, then use structured logs, JVM metrics, trace-like request evidence, and thread state to investigate why errors rose.

## Telemetry Pipeline

```text
Java code
  -> instrumentation
  -> telemetry generation
  -> local collection
  -> processing and filtering
  -> storage
  -> querying
  -> dashboards, alerts, and investigations
```

Instrumentation is the code or configuration that records evidence. Telemetry is the evidence produced: log events, metric observations, span records, health states, diagnostic dumps, and domain events. Collection and storage decide where that evidence goes, how long it remains, and how expensive it is to query.

## Core Signals

| Signal | Example diagnostic use |
| --- | --- |
| Logs | Explain a boundary decision, exception, validation failure, startup event, shutdown event, or configuration change. |
| Metrics | Show rates, totals, concurrency, resource pressure, queue depth, latency distributions, and JVM/runtime health. |
| Traces | Connect work across service, thread, dependency, retry, and asynchronous boundaries. |
| Profiles | Explain where CPU time, allocation, or blocking is concentrated. |
| Dumps | Preserve thread or heap evidence for deeper analysis. |
| Audit trails | Explain who changed what, when, and under which authorization model. |
| Domain signals | Describe business-visible outcomes such as orders accepted, payments declined, or reports delayed. |

The "three pillars" model of logs, metrics, and traces is useful because it gives learners a simple map. It is incomplete because production diagnosis often needs profiles, dumps, deployment records, audit trails, feature flags, runtime configuration, and business events.

## Golden Signals, RED, And USE

| Model | Signals | Best fit |
| --- | --- | --- |
| Golden signals | Latency, traffic, errors, saturation. | User-facing service behavior. |
| RED | Rate, errors, duration. | Request or job processing endpoints. |
| USE | Utilization, saturation, errors. | Resources such as CPU, queues, disks, pools, and executors. |

Leading indicators warn before users feel the full impact, such as queue depth or dependency latency. Lagging indicators show impact after it happened, such as failed orders or missed freshness objectives.

## Review Questions

1. Why is observability more than dashboards and alerts?
2. What is the difference between a symptom and a cause?
3. Why can high-cardinality labels make telemetry expensive or hard to query?
4. When would a thread dump answer a question that logs cannot?
5. Why should instrumentation start from diagnostic questions?

