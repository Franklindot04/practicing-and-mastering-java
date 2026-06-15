# Backend Chart Design Exercises

## Exercise 1: Java Backend Values

Difficulty: Beginner

Concepts practiced: image, ports, profile, probes, resources

Problem statement: Design a small `values.yaml` outline for a Spring Boot backend with image, service port, container port, active profile, probes, and resources.

Hints:
- Group related values.
- Keep defaults local-only.

Stretch challenge: Add release metadata values.

## Exercise 2: ConfigMap Or Secret

Difficulty: Beginner

Concepts practiced: ConfigMap, Secret placeholders, safety

Problem statement: Decide whether each value belongs in a ConfigMap, a Secret example, or nowhere in this repository: Spring profile, log level, real database password, placeholder JWT secret, kubeconfig.

Hints:
- Real credentials do not belong in Git.

Stretch challenge: Explain why rendered manifests can expose secret values.
