# State, Secrets, And Modules Exercises

## Exercise 1: State Review

Difficulty: Beginner

Concepts practiced: state safety, generated files

Problem statement: A learner finds `terraform.tfstate`, `.terraform/`, and `notes.md` after local validation. Which files should never be committed, and why?

Hints: State can contain sensitive or provider-returned values. Generated provider directories are local tool output.

Stretch challenge: Write a short checklist for cleaning the working tree before committing.

## Exercise 2: Module Boundary

Difficulty: Intermediate

Concepts practiced: module inputs, module outputs, reuse

Problem statement: Propose inputs and outputs for a reusable `java-backend-runtime` module. Keep the module focused on runtime metadata, not database creation.

Hints: Inputs should be values callers choose. Outputs should be values callers need.

Stretch challenge: Name one reason not to create the module yet.

## Exercise 3: Secrets Decision

Difficulty: Beginner

Concepts practiced: variables vs secrets, provider credentials

Problem statement: Decide where each value belongs: runtime port, database password, environment name, provider access token. Explain your choices.

Hints: Secrets need protected storage. Ordinary settings can be variables.

Stretch challenge: Explain why `sensitive = true` is not enough by itself.

