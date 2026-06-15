# Desired State, Scaling, And Self-Healing

Most orchestrators work from desired state. You declare what should exist, and the system keeps checking whether the current state matches that declaration.

## Desired State

Desired state is a description of the outcome you want. For example:

- Run three copies of this application
- Expose the application inside the cluster
- Provide these environment variables
- Restart a container when it fails

The orchestrator compares desired state with actual state and makes changes to reduce the difference.

## Self-Healing

Self-healing means the platform can replace failed containers or reschedule workloads without a person manually restarting everything.

This does not mean the application is automatically correct. If the app crashes because of bad configuration or broken code, the orchestrator may keep restarting it until you fix the root cause.

## Scaling

Scaling means changing how much capacity is available. At the beginner level, think of two common forms:

- Horizontal scaling: run more copies of the same workload
- Vertical scaling: give a workload more CPU or memory

Kubernetes can support both patterns, but this stage focuses on the basic idea of multiple replicas and resource planning.

## Why This Matters For Java Backends

Java backend services often need clear runtime configuration, health endpoints, memory planning, and graceful startup behavior. Those habits make the service easier to run under an orchestrator later.
