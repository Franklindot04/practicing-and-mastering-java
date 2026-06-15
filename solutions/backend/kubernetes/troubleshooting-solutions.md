# Kubernetes Troubleshooting Solutions

## Exercise 1

Likely causes include a placeholder image name, a missing tag, a local image that was not loaded into the local cluster, or registry credentials that do not exist. This repo avoids real registry URLs so learners do not confuse examples with deployable production assets.

## Exercise 2

Start with application logs, then inspect events with `describe` or `get events`, then review required environment variables and config references. A bad liveness probe can make the loop worse by restarting the app before it has a fair chance to become healthy.

## Exercise 3

Check readiness endpoint behavior, logs, events, image tag, environment variables, ConfigMap and Secret references, and recent code/config changes. Rollback may not safely undo database migrations, external side effects, or changes in systems outside Kubernetes.
