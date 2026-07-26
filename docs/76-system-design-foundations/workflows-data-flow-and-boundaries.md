# Workflows Data Flow And Boundaries

Before choosing architecture patterns, describe how work moves through the system. Workflows reveal which components are necessary and which are speculative.

## Read Workflows

A read workflow retrieves information without intentionally changing business state.

Example:

```text
User
  |
  v
Task API
  |
  v
Task Query
  |
  v
Task Storage
```

Questions:

- What data is needed?
- Can the response be stale?
- Is the query user-specific?
- Is the query expensive?
- Should the result be cached?

## Write Workflows

A write workflow validates input and changes state.

Example:

```text
User
  |
  v
Task API
  |
  v
Validate Request
  |
  v
Save Task
  |
  v
Return Result
```

Questions:

- What makes the command valid?
- What state changes together?
- What must be idempotent?
- What happens if storage succeeds but a later notification fails?
- Which errors are safe to show to clients?

## Data Flow

Data flow shows where information comes from, where it changes, and where it leaves.

```text
Client Request
  |
  v
DTO -> Validation -> Domain Decision -> Persistence Model -> Response DTO
```

This diagram is simple, but it prevents common confusion between API shape, domain rules, and storage shape.

## Control Flow

Control flow shows who calls whom and when.

```text
Synchronous:
Client -> API -> Storage -> API -> Client

Asynchronous:
API -> Message Boundary -> Worker -> External System
```

Synchronous control flow is easier to reason about for simple cases. Asynchronous control flow can improve decoupling, but it adds duplicate handling, ordering questions, monitoring, and recovery work.

## System Boundaries

A boundary separates what the system owns from what it depends on.

Examples:

- Public API boundary.
- Internal service boundary.
- Database boundary.
- Message broker boundary.
- External payment boundary.
- Authentication provider boundary.

Clear boundaries help teams decide where validation, security, retries, observability, and ownership belong.

## External Dependencies

External dependencies deserve special attention because the system cannot fully control them.

Ask:

- What happens when the dependency is slow?
- What happens when it is unavailable?
- Is a retry safe?
- Is a fallback possible?
- Is the dependency part of the critical path?
- What data crosses the boundary?

## Failure Assumptions

Every design should state what can fail.

- Clients can retry.
- Networks can time out.
- Databases can reject writes.
- A downstream dependency can become slow.
- A queue can build backlog.
- A cache can contain stale data.
- Operators can deploy a bad version.

Design does not remove failure. It makes failure behavior more deliberate.
