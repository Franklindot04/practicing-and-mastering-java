# Consistency Planning

Not every view of task data needs the same consistency.

## Consistency Expectations

| Use Case | Consistency Need | Reason |
| --- | --- | --- |
| Create task response | Strong or read-after-write | User must trust that the task exists |
| Task detail page | Read-after-write | User expects their own changes immediately |
| Dashboard count | Eventual may be acceptable | A short delay is usually tolerable |
| Audit or security decision | Stronger consistency | Stale data can create risk |

## Example

```text
Create task -> task list updates immediately
            -> summary count updates later
```

This may be acceptable if the UI and monitoring understand that summary data is derived.

## Planning Notes

- Record consistency expectations per endpoint or workflow.
- Avoid mixing command decisions with stale reporting data.
- Make delayed views obvious in design docs.
- Test stale-read scenarios before relying on eventual consistency.

