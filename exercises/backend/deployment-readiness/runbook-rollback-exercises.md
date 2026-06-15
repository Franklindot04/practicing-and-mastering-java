# Runbook And Rollback Exercises

## Exercise 1: Smoke Test Plan

Difficulty: Beginner

Concepts practiced: smoke tests, deployment checks

Problem statement: Write four smoke tests for a deployed task API skeleton.

Hints:

- Health endpoint.
- Info or version endpoint.
- One protected endpoint if safe credentials are available.

Stretch challenge: Decide what to do if the version endpoint is wrong.

## Exercise 2: Rollback Trigger

Difficulty: Intermediate

Concepts practiced: rollback, release decision-making

Problem statement: Define three conditions that should trigger rollback or at least pause a rollout.

Hints:

- Think about health, smoke tests, and error rate.
- Include who decides.

Stretch challenge: Add a database migration caution.

## Exercise 3: Release Notes

Difficulty: Beginner

Concepts practiced: release notes, communication

Problem statement: Draft release notes for a demo deployment of a deployment-ready task API.

Hints:

- Include version and commit.
- Include validation.
- Include rollback plan.

Stretch challenge: Add an incident handoff field.
