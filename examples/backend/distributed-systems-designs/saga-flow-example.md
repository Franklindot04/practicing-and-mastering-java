# Saga Flow Example

This example uses a task import workflow with compensation.

```text
1. Create import record
2. Reserve processing slot
3. Store imported task drafts
4. Mark import complete
```

If storing drafts fails after the slot is reserved:

```text
Store drafts fails
       |
       v
Release processing slot
       |
       v
Mark import failed
```

## What To Notice

- Each step can have its own local transaction.
- Compensation is a business action, not a time machine.
- The import record gives operators a place to inspect progress.

## Review Questions

- Which steps are safe to retry?
- Which steps need compensation?
- What state should the user see while the import is incomplete?
- Which failures require manual review?

