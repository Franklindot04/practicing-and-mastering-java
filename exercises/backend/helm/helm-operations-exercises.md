# Helm Operations Exercises

## Exercise 1: Rendered Manifest Review

Difficulty: Beginner

Concepts practiced: helm template, manifest inspection

Problem statement: After running `helm template`, list six things you should inspect in the rendered YAML.

Hints:
- Think image, labels, selectors, config, probes, resources.

Stretch challenge: Explain why lint passing is not enough.

## Exercise 2: Upgrade And Rollback Reasoning

Difficulty: Beginner

Concepts practiced: upgrades, rollbacks, release history

Problem statement: A chart upgrade changes the image tag and readiness probe path. What should you review before deciding the change is safe?

Hints:
- Compare rendered manifests.
- Think about smoke tests.

Stretch challenge: Name one change a Helm rollback may not undo.
