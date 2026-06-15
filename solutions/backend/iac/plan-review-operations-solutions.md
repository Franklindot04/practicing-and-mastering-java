# Plan Review And Operations Solutions

## Exercise 1

Reviewer questions:

- Is replacement intended?
- What data could be lost?
- Is this the correct environment?
- Are backups and restore steps tested?
- Is there a safer migration path?

A small code change can force replacement if it changes an immutable resource attribute.

## Exercise 2

The real network setting may have been changed manually or during an emergency. The team should identify who changed it and why, decide whether code or reality is correct, and reconcile the difference through review.

A safe follow-up task: document the decision and add a pull request if the IaC should reflect the emergency change.

## Exercise 3

A good readiness plan names runtime settings, database ownership, private/public network boundaries, secret storage, state backend expectations, and pull request review steps.

Service mesh, advanced cloud networking, and production multi-region design should wait for later stages.

