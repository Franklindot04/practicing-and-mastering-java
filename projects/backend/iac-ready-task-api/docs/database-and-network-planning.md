# Database And Network Planning

Persistent backends often need a database and network rules. IaC can describe those pieces, but beginners should plan before writing provider-specific resources.

## Database Planning

Decide:

- Which environment needs persistence.
- Whether the database is managed by a platform or provided separately.
- How migrations are handled.
- How backups and restores are tested.
- Where connection settings come from.

Do not commit real database URLs or passwords. Use placeholders in examples.

## Network Planning

Decide:

- Which services should be reachable publicly.
- Which services should stay private.
- Which ports are required.
- How health checks reach the app.
- Which dependencies need outbound access.

Network changes can expose systems unexpectedly. Treat plan review as a security practice, not just a syntax check.

