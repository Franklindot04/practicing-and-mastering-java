# Consistency Models

A consistency model describes what readers can expect after data changes.

## Strong Consistency

Strong consistency means that after a write succeeds, later reads see that write.

This is easier for users to understand, but it can require coordination between nodes. Coordination can increase latency or reduce availability during failures.

Good fits:

- Bank transfers.
- Inventory reservations.
- Permission changes.
- Password resets.

## Eventual Consistency

Eventual consistency means replicas may temporarily disagree, but if no new writes happen, they should converge over time.

This can improve availability and scale, but the application must tolerate stale reads.

Good fits:

- Search indexes.
- Analytics counters.
- Notification feeds.
- Cached profile summaries.

## Read-After-Write Consistency

Read-after-write consistency means a user who just changed data can immediately see their own change, even if other users may briefly see older data.

This is common in user-facing systems because it protects trust in the interface.

Example:

```text
User updates profile name.
User refreshes profile page.
The user should see the new name.
Other views may catch up shortly after.
```

## Choosing A Model

Ask:

- Would stale data confuse users?
- Could stale data create financial, security, or safety risk?
- Can the UI explain delayed updates?
- Can the system repair conflicts?
- How will you test the delayed state?

