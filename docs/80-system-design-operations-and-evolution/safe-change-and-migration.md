# Safe Change And Migration

Systems must change while users keep using them. Safe change is a design concern.

## Safe Deployments

Common concepts:

- Small releases.
- Automated tests.
- Smoke checks.
- Feature flags.
- Canary rollout.
- Blue-green rollout.
- Clear rollback procedure.

These are concepts in this section, not deployable production manifests.

## Feature Flags

Feature flags let teams separate deployment from exposure.

Design questions:

- What is the default value?
- Who can change the flag?
- How is flag state audited?
- When will the flag be removed?

## Backward Compatibility

Backward compatibility matters when old and new clients or services run at the same time.

Examples:

- Add optional fields before requiring them.
- Accept both old and new event versions temporarily.
- Keep removed fields readable during migration.
- Avoid changing meaning under the same name.

## API Versioning

Version APIs when clients cannot all move together. Versioning is not a substitute for clear compatibility promises.

## Database Migrations

Safer migration flow:

```text
Expand schema -> Write compatible data -> Backfill -> Read new shape -> Contract old shape
```

## Rolling Migrations

Rolling migrations allow old and new application versions to coexist. This requires careful schema and contract design.

## Rollback Limits

Rollback may be impossible after:

- Destructive schema changes.
- One-way data transformations.
- External side effects.
- Event versions consumed by other systems.

Name rollback limits before deployment.
