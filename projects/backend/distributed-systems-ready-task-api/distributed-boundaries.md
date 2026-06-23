# Distributed Boundaries

Distributed readiness starts by naming boundaries. A boundary is not automatically a new service. It is a responsibility that may later need separate ownership, scaling, data, or failure handling.

## Candidate Boundaries

| Boundary | Current Shape | Future Question |
| --- | --- | --- |
| Task management | Core API behavior | Should it remain the system of record? |
| Notifications | Could be async later | Can notification failure be decoupled from task creation? |
| Reporting | Could use derived data | Can reports lag behind writes? |
| Identity | Security stage concept | Should identity remain external to task ownership logic? |

## Boundary Diagram

```text
Task API owns task commands
Task API publishes facts later if needed

TaskCreated
TaskCompleted
TaskDeleted
```

## Readiness Questions

- Which data does the Task API own?
- Which data is derived?
- Which responsibilities can fail without failing the main user request?
- Which future boundaries need idempotency?
- Which boundaries would create unacceptable consistency risk?

