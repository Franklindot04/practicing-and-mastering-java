# Probes, Resources, And Ports

Java backend charts should make health probes and resource settings visible without making the chart overly complex.

## Health Probes

Common values:

```yaml
probes:
  readinessPath: /actuator/health/readiness
  livenessPath: /actuator/health/liveness
  startupPath: /actuator/health
```

Readiness controls traffic. Liveness controls restarts. Startup protects slow-starting applications from early liveness failures.

## Resource Requests And Limits

Resource values usually include CPU and memory requests and limits.

```yaml
resources:
  requests:
    cpu: 100m
    memory: 256Mi
  limits:
    cpu: 500m
    memory: 512Mi
```

Java memory limits should be chosen carefully because a JVM can fail when the container memory limit is too low.

## Ports

Keep port values simple:

- container port: where the Java app listens
- service port: where the cluster Service exposes it

## Common Mistakes

- Probe endpoints requiring authentication
- Liveness checks starting too soon
- Resource limits copied from another app without review
- Service target ports that do not match container ports
