# Event-Driven Architecture Answer Key

## Multiple Choice

1. B
2. B
3. A
4. A
5. B
6. B
7. A
8. B

## Short Answer

1. An event states that something happened; a command asks something to do work.
2. Sending a notification, updating reporting, writing an audit entry, or updating an activity feed.
3. It should process idempotently, skip work already done, or treat the duplicate as success when the intended result already exists.
4. Events represent history. Corrections should add new events instead of rewriting old facts.
5. Newer consumers can still read older event shapes.
6. Older consumers can tolerate newer event shapes, usually by ignoring unknown fields.
7. The workflow is spread across event reactions, so the full process may be hard to see in one place.
8. An orchestrator coordinates workflow steps and decides what happens next.
9. One business action writes to two independent places, and one write can succeed while the other fails.
10. Useful signals include unprocessed event age, consumer failure count, retry count, duplicate count, poison event count, and processing latency.

## Design Reading

1. The Task API.
2. Notification, reporting, and audit consumers.
3. Task saving and event creation should not proceed if validation fails.
4. Usually no. Task creation can remain valid while notification failure is retried or investigated.
5. Event ID, correlation ID, task ID, event type, version, and timestamps.
6. A duplicate `TaskCreated` could create duplicate notifications unless the consumer deduplicates.
7. It is closer to publish/subscribe because one event is consumed by multiple independent consumers.
8. Oldest unprocessed reporting event age or reporting consumer lag.
9. Renaming a required field, removing a field, or changing a field's meaning.
10. The API would save the task and an outbox record together, then publish from the outbox later.
