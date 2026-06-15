# Configuration, Probes, And Resources Exercises

## Exercise 1: ConfigMap Or Secret

Difficulty: Beginner

Concepts practiced: ConfigMaps, Secrets, environment variables

Problem statement: Decide whether each value belongs in a ConfigMap, a Secret, or neither in this repository:

- `SPRING_PROFILES_ACTIVE`
- demo log level
- real database password
- placeholder demo token
- private cloud key

Hints:
- Real credentials do not belong in this repository.
- Placeholder examples must be clearly marked.

Stretch challenge: Explain why base64 is not encryption.

## Exercise 2: Probe Choice

Difficulty: Beginner

Concepts practiced: readiness, liveness, startup probes

Problem statement: A Java API takes 30 seconds to start. Which probe can protect it from being restarted too early, and why?

Hints:
- Readiness controls traffic.
- Liveness can restart a container.
- Startup gives slow apps time.

Stretch challenge: Explain why a probe endpoint should not require authentication.

## Exercise 3: Resource Planning

Difficulty: Beginner

Concepts practiced: CPU requests, memory limits, JVM behavior

Problem statement: Explain the difference between a resource request and a resource limit for a Java backend Pod.

Hints:
- One helps scheduling.
- One is an enforcement boundary.

Stretch challenge: Describe a symptom of setting the memory limit too low.
