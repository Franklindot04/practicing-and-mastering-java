# Deployment Checklists And Runbooks

A checklist helps prevent missed steps. A runbook explains what to do when the deployment is routine or when something goes wrong.

These notes are educational and vendor-neutral. They do not create deployment automation or real cloud resources.

## Files

- [Pre-Deployment Checklist](pre-deployment-checklist.md)
- [Post-Deployment Smoke Tests](post-deployment-smoke-tests.md)
- [Rollback Runbook](rollback-runbook.md)
- [Release Notes Template](release-notes-template.md)

## Runbook Basics

A useful runbook names:

- Goal
- Owner
- Preconditions
- Steps
- Verification
- Rollback plan
- Communication notes

## Common Runbook Mistakes

- Steps are vague.
- No owner is listed.
- Rollback decision is unclear.
- Secrets are pasted into docs.
- Smoke tests are not defined.
- Database changes are not called out.
