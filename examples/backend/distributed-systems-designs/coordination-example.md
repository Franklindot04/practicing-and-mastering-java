# Coordination Example

This example shows one scheduled cleanup task that should run once.

```text
API instance A \
API instance B  +--> try to become cleanup leader
API instance C /

Only the elected leader runs cleanup.
```

## Failure Case

```text
Leader A pauses
Leader lease expires
Instance B becomes leader
Leader A resumes
```

If A keeps working, two leaders may perform the same cleanup. The design needs a way to prevent stale work, such as lease checks, fencing tokens, or idempotent cleanup behavior.

## Review Questions

- Does cleanup really need distributed coordination?
- Could the task be made idempotent instead?
- What happens if no leader exists temporarily?
- What prevents stale leaders from writing late results?

