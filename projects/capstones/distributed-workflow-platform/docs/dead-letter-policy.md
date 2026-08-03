# Dead-Letter Policy

Poison tasks and irreversible failures enter quarantine. Quarantine is for investigation, replay decisions, and operator review, not automatic success.

## Purpose

Dead-letter handling protects the system from repeated harm. A task that consistently fails because of invalid input or unsafe side effects should not consume worker capacity forever.

## Quarantine Triggers

- Poison task classification.
- Irreversible step failure.
- Retry exhaustion in a future extended policy.
- Contract or payload validation failure in a real transport layer.

## Operator Workflow

Quarantined tasks should be grouped by workflow, task type, reason, and age. Operators decide whether to repair data, deploy a code fix, replay safely, compensate, or discard. Replay requires proof that side effects are idempotent or already compensated.

## Trade-Offs

Quarantine delays workflow completion, but protects dependencies and customer state. Automatic replay can recover faster, but it can also repeat damage if the failure is deterministic.

## Production Comparison

A production dead-letter system would store payloads durably, redact sensitive fields, track ownership, alert on age and volume, and preserve decisions. This capstone stores local quarantine state only.
