# Retention Recovery And Migration

Data has a lifecycle. A design should explain how long data lives, how it is protected, and how it changes safely over time.

## Data Retention

Retention defines how long data is kept.

Examples:

- Active orders are kept indefinitely for user history.
- Debug logs are kept briefly.
- Audit events are kept according to policy.
- Expired idempotency keys are removed after a safe window.

Retention choices affect cost, privacy, query performance, and recovery.

## Archival

Archival moves rarely used data out of hot paths.

Ask:

- Who still needs the archived data?
- How quickly must it be restored?
- Can archived data be anonymized?
- Do old records still need schema compatibility?

## Backup And Recovery Concepts

Backups are useful only if recovery is practiced.

Review:

- What is backed up?
- How often are backups created?
- How is restoration tested?
- What data might be lost between backups?
- Who owns recovery during an incident?

## Data Migrations

Data migrations change stored data or schema.

Safer migrations are usually incremental:

```text
Add new field -> Write both shapes -> Backfill -> Read new field -> Stop writing old field -> Remove old field
```

This is often called expand-and-contract. It reduces the need for risky big-bang changes.

## Online Schema Evolution

Online schema evolution keeps the system running while schema changes roll out.

Questions:

- Can old and new application versions run at the same time?
- Are new fields optional during rollout?
- Can reads tolerate missing data?
- Is rollback still possible after the migration?

## Recovery Concepts

Recovery planning should include:

- Restore from backup.
- Rebuild read models.
- Replay events where safe.
- Repair corrupted records.
- Reconcile mismatched systems.

Do not assume every recovery path is automatic. Some designs need documented manual steps.
