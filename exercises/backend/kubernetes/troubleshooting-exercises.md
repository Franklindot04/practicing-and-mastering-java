# Kubernetes Troubleshooting Exercises

## Exercise 1: ImagePullBackOff

Difficulty: Beginner

Concepts practiced: image names, tags, local images

Problem statement: A Pod shows `ImagePullBackOff` for image `example/deployment-ready-task-api:local`. List three likely causes.

Hints:
- The image name is a placeholder.
- Local clusters may not see your local Docker image automatically.

Stretch challenge: Explain why this repo avoids real registry URLs.

## Exercise 2: CrashLoopBackOff

Difficulty: Beginner

Concepts practiced: logs, config mistakes, restarts

Problem statement: A Pod starts and then restarts repeatedly. Describe the first three things you would inspect.

Hints:
- Logs explain the application process.
- Events explain Kubernetes behavior.
- Environment variables can break startup.

Stretch challenge: Explain how a bad liveness probe can make this worse.

## Exercise 3: Rollout Reasoning

Difficulty: Beginner

Concepts practiced: rollout status, rollback, smoke tests

Problem statement: A new Deployment version passes creation but fails readiness. What should you check before deciding whether to roll back?

Hints:
- Readiness endpoint, logs, events, and config are all relevant.
- Rollback is not a fix for every change.

Stretch challenge: Name one kind of change rollback may not safely undo.
