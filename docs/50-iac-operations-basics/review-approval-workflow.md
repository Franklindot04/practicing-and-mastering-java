# Review And Approval Workflow

IaC changes deserve careful review because they can alter infrastructure, security boundaries, and data durability.

## Pull Request Workflow

A beginner-friendly workflow looks like:

1. Create a focused branch.
2. Change IaC files.
3. Run formatting and safe validation.
4. Open a pull request.
5. Attach or discuss a plan when appropriate.
6. Review the code and the proposed infrastructure changes.
7. Apply only through an approved workflow.

## What Reviewers Look For

- Does the change match the stated goal?
- Does the plan include unexpected deletes or replacements?
- Are secrets kept out of Git and outputs?
- Are environment names clear?
- Are permissions least-privilege?
- Is the rollback or recovery path understood?

## Change Approval

Approval is not just a click. It means the reviewer understands the risk, scope, and expected result. Production IaC usually needs stricter review than development IaC.

## Rollback And Recovery Concepts

Application rollback often means deploying an older artifact. Infrastructure rollback can be harder. Some changes cannot be reversed without data restore, manual cleanup, or migration work.

Plan recovery before applying risky changes.

