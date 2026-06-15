# Plan Review And Operations Exercises

## Exercise 1: Plan Reasoning

Difficulty: Beginner

Concepts practiced: plan review, replacement risk

Problem statement: A plan says a database-like resource will be replaced. Write three questions a reviewer should ask before approving.

Hints: Think about data loss, backups, environment, and whether replacement was intended.

Stretch challenge: Add one reason a plan might show replacement even if the code change looks small.

## Exercise 2: Drift Reasoning

Difficulty: Beginner

Concepts practiced: drift, manual changes

Problem statement: A plan shows a network setting changing back to match code, but nobody changed the IaC files. Explain what may have happened and how the team should respond.

Hints: Drift can come from manual dashboard changes or emergency fixes.

Stretch challenge: Write a safe follow-up task after the drift is understood.

## Exercise 3: Java Backend Infrastructure Plan

Difficulty: Intermediate

Concepts practiced: backend runtime planning, database planning, environment structure

Problem statement: Draft a short IaC readiness plan for the task API. Include runtime, database, network, secrets, state, and review workflow.

Hints: Reference concepts from deployment-ready, Kubernetes-ready, and Helm-ready stages.

Stretch challenge: Identify one topic that should wait for a later advanced cloud or production infrastructure stage.

