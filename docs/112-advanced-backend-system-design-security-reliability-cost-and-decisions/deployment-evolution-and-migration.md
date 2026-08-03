# Deployment Evolution And Migration

Architecture changes while the product is running. Good designs include compatibility windows, migration paths, and rollback limits before the first release.

## Compatibility

Backward-compatible rollouts let old and new versions coexist. Additive API and event fields are usually safer than removing or renaming fields. Consumers should be tolerant readers where appropriate, and producers should not emit incompatible contracts until consumers are ready.

Schema rollout often follows expand-contract:

1. expand schema with new nullable fields or tables
2. deploy code that writes old and new shapes if necessary
3. backfill existing data with validation
4. switch reads
5. stop old writes
6. remove old schema only after compatibility windows close

## Migration Patterns

Strangler migrations route selected capabilities from an old system to a new one. They need routing rules, observability, fallback, data synchronization, and ownership clarity.

Modular-monolith extraction should start with internal boundaries. Prove module APIs, data ownership, and operational need before making a network boundary.

Deprecation should include user communication, telemetry, compatibility duration, owner approval, and rollback considerations.

## Release Sequencing

Feature flags can decouple deploy from release. They also add state combinations, cleanup work, and operational responsibility.

Rollbacks are constrained by database changes, event emissions, external side effects, mobile clients, and contract changes. Roll-forward may be safer when a bad version has already written new data.

## Evolution Reviews

Regular architecture reviews should ask what has changed:

- traffic and workload
- team ownership
- cost
- incident history
- security requirements
- data retention
- regulatory constraints
- coupling
- operational pain

Technical debt should be made explicit with risk, cost, owner, and review date. Not all debt must be fixed immediately, but hidden debt becomes architecture drift.
