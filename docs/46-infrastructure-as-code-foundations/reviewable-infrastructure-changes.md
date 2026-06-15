# Reviewable Infrastructure Changes

IaC turns infrastructure changes into text changes. That makes infrastructure part of the same engineering workflow used for application code.

## What A Review Can Catch

A pull request review can catch:

- A public network rule that should be private.
- A database setting that may lose data.
- A resource name that does not match the environment.
- A missing variable description.
- A value that should be passed as a secret instead of committed.
- A change that may destroy and recreate infrastructure.

The most important review question is simple: does the planned change match the intention?

## Plan Before Apply

A plan is a preview of what an IaC tool would change. Plans are not a substitute for judgment. They are evidence to review.

In real teams, a safe workflow often looks like:

1. Change IaC files.
2. Format and validate locally.
3. Open a pull request.
4. Review the diff and a plan.
5. Approve the change.
6. Apply with the right identity and environment controls.

This repository does not run real applies. Keep the examples local and educational.

## Common Beginner Mistakes

- Treating a plan as automatically safe.
- Committing generated state or plan files.
- Mixing real credentials into examples.
- Reusing production names in demos.
- Forgetting that infrastructure changes can cost money.
- Copying provider snippets without understanding required permissions.

