# Common Kubernetes Failures

## ImagePullBackOff

Kubernetes cannot pull the container image.

Possible causes:

- Image name is wrong
- Tag does not exist
- Registry requires credentials
- Local-only image was not loaded into the local cluster

## CrashLoopBackOff

The container starts and then exits repeatedly.

Possible causes:

- Application startup exception
- Missing environment variable
- Bad database connection setting
- Memory limit too low
- Command or entrypoint error

## Pending Pods

The Pod has not been scheduled.

Possible causes:

- Not enough CPU or memory available
- Node constraints cannot be satisfied
- Local cluster is not healthy

## Failing Probes

Readiness, liveness, or startup probes are failing.

Possible causes:

- Wrong path
- Wrong port
- Endpoint requires authentication
- Java application needs more startup time
- App is unhealthy because of configuration

## Config Or Environment Mistakes

Common examples:

- ConfigMap name does not match the Deployment reference
- Secret name does not match the Deployment reference
- Required environment variable is absent
- Value exists but has the wrong format
