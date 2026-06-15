# Drift, Import, And Failed Apply

Real infrastructure does not always stay perfectly aligned with IaC files.

## Drift Detection

Drift happens when real infrastructure differs from configuration or recorded state. A plan can reveal drift by showing changes that the team did not expect.

Drift review questions:

- Was this changed manually?
- Was it an emergency fix?
- Should the code be updated to match reality?
- Should the real resource be changed back to match code?

## Import Concept

Import connects an existing real resource to IaC state. It can be useful when a team wants to start managing existing infrastructure with IaC.

Import is not beginner magic. It requires careful mapping, state review, and a plan after import. Importing the wrong thing can create confusion or risk.

## Failed Applies

A failed apply can leave some changes completed and others incomplete. The next step is not always to run the same command again.

Safer response:

1. Read the error.
2. Check what changed.
3. Review state.
4. Create a recovery plan.
5. Ask for help before touching production.

## Why Production Needs Deeper Review

Production IaC affects users, data, cost, security, and compliance. Mature teams use stronger controls, separation of duties, monitoring, backups, incident processes, and audited automation.

