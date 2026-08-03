# Runbooks

- Lease expiration: inspect task id, worker id, attempt count, and expiration time before reassignment.
- Poison task: quarantine, inspect payload and handler behavior, then decide whether to repair, replay, or discard.
- Hot partition: identify partition owner, pending task count, and whether routing or sharding should change.
- Admission rejection: confirm capacity pressure and prefer bounded rejection over unbounded queue growth.

## Incident Triage Flow

1. Identify whether the symptom is stuck workflow, retry storm, duplicate completion, hot partition, quarantine growth, or admission rejection.
2. Check operational report counts and relevant state records.
3. Decide whether the next safe action is wait, reassign, quarantine, replay, compensate, or reject new work.
4. Record the assumption that made the action safe.

## Production Comparison

A real runbook would include dashboard links, query examples, worker ownership, customer impact, rollback criteria, replay approval, and communication ownership. This local runbook focuses on reasoning steps that can be validated in tests.
