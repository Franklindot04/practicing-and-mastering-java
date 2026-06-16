# Service Boundaries And Communication

A service mesh is most useful when several services communicate often enough that shared policy becomes valuable. The task API starts as one service, so learners should first reason about boundaries.

## Current Boundary

The task API owns:

- Task creation and updates.
- Validation.
- Persistence decisions.
- User-facing task behavior.

## Possible Future Dependencies

Conceptual future dependencies might include:

- A notification service.
- An audit service.
- A reporting service.
- A user profile service.

These are design examples, not required implementation work.

## Boundary Questions

- Does the dependency own a separate business capability?
- Can it fail without corrupting task state?
- Is the call synchronous or asynchronous?
- Does the caller need a fallback?
- Which team owns the API contract?

