# Error Handling And Validation Hardening

Production-style APIs treat every request as untrusted input. Validation and error handling make invalid requests predictable instead of surprising.

## Validation Hardening

Validation starts at the boundary:

- Request DTOs check required fields, size limits, and simple formats.
- Controllers accept only the data they need.
- Services enforce business rules that depend on existing state.
- Repositories should not receive unchecked or surprising values from higher layers.

Validation annotations are helpful, but they are not the whole system. A title length can be checked with `@Size`, but a rule like "a completed task cannot be edited by a read-only user" belongs in service or authorization logic.

## Safer Error Messages

A useful error response tells the client what kind of problem happened without exposing private internals.

Prefer messages like:

- `Task was not found.`
- `Title must be between 1 and 80 characters.`
- `You are not allowed to update this task.`

Avoid messages like:

- SQL statements or table names.
- Full stack traces.
- Raw exception class names.
- Password hashes, tokens, or configuration values.
- Details that reveal whether a private user account exists.

## Consistent Error Shape

A consistent shape makes clients easier to build:

```json
{
  "status": 400,
  "message": "Validation failed.",
  "path": "/api/tasks",
  "timestamp": "2026-06-15T08:00:00Z"
}
```

Learning projects can keep this simple. Real production systems often add request IDs, error codes, documentation links, and localization rules.

## Graceful Failure Basics

Graceful failure does not mean hiding every error. It means failing in a way that helps recovery.

- If required configuration is missing, fail during startup with a clear message.
- If a client sends invalid input, return a clear 400 response.
- If a resource does not exist, return 404 instead of a generic 500.
- If an unexpected server error happens, log enough for maintainers and return a safe generic response to the client.

## Common Mistakes

- Catching every exception and returning 200.
- Returning stack traces to clients.
- Logging the full request body when it may contain secrets.
- Trusting client-provided roles or user IDs without server-side checks.
- Relying only on frontend validation.
- Treating validation annotations as a replacement for business rules.
