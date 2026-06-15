# Rollback Basics

Rollback means returning to a previous known-good version or configuration.

## When To Consider Rollback

- Health checks fail.
- Smoke tests fail.
- Error rate rises.
- Critical user workflow is broken.
- Required configuration is wrong and cannot be fixed safely in place.

## Rollback Inputs

Before deploying, know:

- The previous known-good version.
- How to redeploy it.
- Whether database changes are backward compatible.
- Who decides to roll back.
- How to communicate the decision.

## Database Caution

Database changes can make rollback harder. A code rollback is simpler when schema changes are backward compatible.

This repository does not add real migration tooling yet. Treat database migration planning as a later production topic.

## Common Mistakes

- No rollback plan.
- Rolling back code but not configuration.
- Forgetting database compatibility.
- Waiting too long while users are affected.
- Hiding rollback details from release notes.
