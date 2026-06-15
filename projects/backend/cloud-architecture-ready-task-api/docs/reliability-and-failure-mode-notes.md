# Reliability And Failure Mode Notes

## Failure Modes

- One task API instance fails.
- Database is slow.
- Database is unavailable.
- A deployment introduces a bad version.
- Logs or metrics become unavailable.
- Queue workers fall behind if async jobs are added later.

## Graceful Degradation

The task API has limited graceful degradation because core features depend on the database. Read-only fallback might be possible for cached views, but task creation needs durable storage.

## Recovery Questions

- How is a bad instance removed from traffic?
- How is a bad deployment rolled back?
- How are failed writes detected?
- Who follows the runbook during an incident?

