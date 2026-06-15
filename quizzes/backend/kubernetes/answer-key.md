# Kubernetes Foundation Quiz Answer Key

## Orchestration And Kubernetes Basics

1. B
2. A
3. B
4. A container image packages what runs, but a deployment plan also needs replica count, networking, configuration, health checks, resources, and update behavior.
5. Examples: kind, minikube, Docker Desktop Kubernetes.
6. The plan is unsafe because it uses a shared/cloud cluster for beginner examples and copies real credentials into YAML. Use local-only learning resources and placeholders only.

## Workloads, Networking, And Configuration

1. A
2. A
3. A
4. Because repository files can be copied, shared, and preserved in history. Secret examples must use placeholders only; real secret management belongs elsewhere.
5. Readiness controls whether traffic should go to a Pod. Liveness controls whether Kubernetes should restart the container.
6. The Service selector does not match the Pods, so the Service will not route traffic to them.
7. Kubernetes may restart the Java process before it has enough time to start, causing repeated failures.

## Operations And Troubleshooting

1. A
2. A
3. A
4. Inspect application logs, Kubernetes events or `describe` output, environment/config references, image settings, probes, and resource limits.
5. It removes the grouped local learning resources together and reduces the chance of accidentally deleting unrelated objects.
6. Inspect readiness endpoint behavior, logs, events, ConfigMap or Secret references, image version, and recent application/config changes.
7. Examples: monitoring, alerting, capacity planning, security controls, network policy, cluster upgrades, backup and recovery, incident response.
