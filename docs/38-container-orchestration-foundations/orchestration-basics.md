# Orchestration Basics

A container packages an application with the files it needs to run. Orchestration manages many containers across an environment so the application can keep running when machines change, containers stop, or traffic grows.

## Why Orchestration Exists

Running one container locally is useful for learning and development. Real services usually need more:

- Repeatable startup and shutdown
- Multiple running copies
- Health checks
- Network discovery between services
- Configuration without rebuilding images
- Replacement when a container fails
- Controlled updates

An orchestrator watches the system and keeps it close to the state you requested.

## Containers Vs Orchestration

A container answers: "What process should run, and with which filesystem?"

An orchestrator answers: "How many copies should exist, where should they run, how should they be reached, and what should happen if one fails?"

## Scheduling Basics

Scheduling is the process of choosing a machine for a workload. A scheduler considers available CPU, memory, constraints, and health of the machines.

For a beginner, the key idea is simple: you describe the workload, and the platform chooses a reasonable place to run it.

## Common Beginner Mistakes

- Treating a container image as a complete deployment plan
- Hard-coding configuration into the image
- Assuming one running container is enough for reliability
- Ignoring health checks
- Forgetting that logs, metrics, and events matter after startup
- Copying production-looking YAML without understanding the fields

## Educational Scope

The Kubernetes examples in this repository are local-only and intentionally small. They are designed to help you read and reason about orchestration before you try production Kubernetes.
