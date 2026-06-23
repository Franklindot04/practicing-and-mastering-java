# Failure Planning

Failure planning describes what the task API should do when a dependency is slow, unavailable, stale, or uncertain.

## Failure Table

| Failure | User Impact | Planned Response |
| --- | --- | --- |
| Task data store unavailable | Core task operations fail | Return safe error and alert |
| Notification boundary unavailable | Task can still be created | Store pending notification or skip with visible status |
| Reporting projection delayed | Dashboard stale | Show last updated time or accept lag |
| Duplicate create request | Could create duplicate task | Use idempotency key and return stored result |
| Unknown workflow state | User may be confused | Mark needs review and expose operator status |

## Recovery Sketch

```text
Task created
Notification failed
Retry attempts exhausted
Status: notification_failed
Operator can inspect and retry manually
```

## Planning Checklist

- Define degraded behavior before implementation.
- Separate core user actions from optional side effects.
- Prefer explicit status over hidden background failure.
- Document manual recovery paths for uncertain states.
- Keep examples vendor-neutral and free of real infrastructure details.

