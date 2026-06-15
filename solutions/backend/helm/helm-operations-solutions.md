# Helm Operations Solutions

## Exercise 1

Inspect image name and tag, Service selectors, Pod labels, ConfigMap values, Secret placeholders, probe paths, resource settings, ports, and namespace assumptions. Lint passing is not enough because rendered YAML can still describe the wrong application behavior.

## Exercise 2

Review the rendered Deployment, image tag, readiness path, app health endpoint, release notes, smoke tests, logs, and rollback plan. Helm rollback may not undo database migrations, external side effects, or values managed outside the chart.
