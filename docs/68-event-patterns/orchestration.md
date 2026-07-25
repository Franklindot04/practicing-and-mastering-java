# Orchestration

Orchestration is a workflow style where one coordinator decides the next steps and asks participants to perform work.

## Concept

```text
Workflow coordinator
  |
  +--> Request payment
  |
  +--> Reserve inventory
  |
  +--> Send notification
```

The coordinator holds the workflow logic.

## Benefits

- The overall process is easier to inspect.
- Failure handling can be centralized.
- Business order can be explicit.
- Operators may have one place to understand workflow state.

## Tradeoffs

- The coordinator can become too powerful or too coupled.
- Participants may become passive service endpoints.
- Changes to workflow rules may require changing the coordinator.

## Choreography Vs Orchestration

| Question | Choreography | Orchestration |
| --- | --- | --- |
| Who drives the flow? | Event-reacting participants | A coordinator |
| Visibility | Spread across events | Central workflow state |
| Coupling risk | Hidden event chains | Overgrown coordinator |
| Best for | Independent reactions | Explicit multi-step process |

Neither pattern is always better. Choose based on workflow clarity, ownership, failure handling, and operational visibility.
