# Configuration, Probes, And Resources Solutions

## Exercise 1

- `SPRING_PROFILES_ACTIVE`: ConfigMap
- demo log level: ConfigMap
- real database password: neither; do not commit it
- placeholder demo token: Secret example file, clearly marked
- private cloud key: neither; do not commit it

Base64 is encoding, not encryption. Anyone can decode it.

## Exercise 2

A startup probe protects a slow-starting Java API from liveness checks until startup succeeds. Readiness decides whether traffic should be sent. Liveness decides whether the container should be restarted.

Probe endpoints should not require authentication because Kubernetes needs to call them directly and reliably.

## Exercise 3

A request tells Kubernetes what the workload expects and helps with scheduling. A limit is the maximum the container can use. If a Java memory limit is too low, the app may restart or fail with memory errors even when the code is otherwise correct.
