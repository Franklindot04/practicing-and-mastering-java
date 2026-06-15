# Environment Folder Structure

Environment folders separate configuration for different deployment targets.

## Common Environment Names

- `dev` for everyday development.
- `test` for automated or integration testing.
- `staging` for production-like review.
- `prod` for real users and real data.

Not every project needs all of these. A learning repo may only need local examples.

## Example Shape

```text
infra/
  modules/
    java-backend-runtime/
  environments/
    dev/
    test/
    staging/
    prod/
```

Each environment folder can call shared modules with different values.

## Shared Vs Environment-Specific Configuration

Shared modules should contain reusable shape. Environment folders should contain choices that differ by environment, such as names, sizes, replica counts, feature flags, or approved network boundaries.

Avoid putting production secrets into environment folders. Real secrets belong in approved secret storage.

## Naming Conventions

Use names that show purpose and environment. Clear names reduce the chance of applying changes to the wrong place.

Examples:

- `task-api-dev`
- `task-api-staging`
- `task-api-prod`

These are naming examples only, not real resources.

