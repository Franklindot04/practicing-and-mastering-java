# Orchestration Basics Exercises

## Exercise 1: Container Orchestration Vocabulary

Difficulty: Beginner

Concepts practiced: containers, orchestration, desired state, scheduling

Problem statement: Explain the difference between running one container manually and using an orchestrator to run a backend service.

Hints:
- Think about restarts, scaling, placement, and configuration.
- Separate what the image contains from how the app is operated.

Stretch challenge: Write three questions you would ask before deciding whether a service needs orchestration.

## Exercise 2: Desired State Review

Difficulty: Beginner

Concepts practiced: desired state, actual state, reconciliation

Problem statement: A Deployment says there should be three replicas, but only two Pods are running. Describe what Kubernetes should try to do and what you would inspect if it cannot.

Hints:
- Desired state is the target.
- Events often explain why the target cannot be reached.

Stretch challenge: List two reasons a third Pod might stay Pending.

## Exercise 3: Local-Only Safety

Difficulty: Beginner

Concepts practiced: safe local learning, cluster boundaries

Problem statement: Create a short safety checklist for trying demo manifests locally.

Hints:
- Include namespace, credentials, and cleanup.
- Mention shared or production clusters.

Stretch challenge: Add a rule about kubeconfig files.
