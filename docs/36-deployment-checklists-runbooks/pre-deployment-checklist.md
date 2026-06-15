# Pre-Deployment Checklist

Use this before deploying a learning backend to any shared environment.

## Code And Build

- [ ] Correct branch or commit selected.
- [ ] Tests passed.
- [ ] Artifact version recorded.
- [ ] No generated `target/`, `.class`, or build artifacts committed.
- [ ] No `.env`, private keys, cloud keys, tokens, or real credentials committed.

## Runtime Configuration

- [ ] Required environment variables documented.
- [ ] Secrets are supplied outside Git.
- [ ] Active profile is known.
- [ ] Port configuration is known.
- [ ] Database connection strategy is documented.

## Database Caution

- [ ] Schema changes are reviewed.
- [ ] Backward compatibility is considered.
- [ ] Backup/restore expectations are known.
- [ ] No real migration tooling is assumed in this stage.

## Rollback Readiness

- [ ] Previous known-good version identified.
- [ ] Rollback owner identified.
- [ ] Rollback trigger conditions listed.
- [ ] Communication path is known.
