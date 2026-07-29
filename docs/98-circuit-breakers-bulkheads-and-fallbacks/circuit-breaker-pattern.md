# Circuit Breaker Pattern

Reliability work starts by naming the behavior precisely. A vague statement such as `the service is down` is less useful than a statement about the caller, dependency, symptom, time window, and recovery expectation.

## Closed State

Closed State describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Open State

Open State describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Half-Open State

Half-Open State describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Failure Thresholds

Failure Thresholds describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Rolling Windows

Rolling Windows describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Minimum Call Counts

Minimum Call Counts describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Open Duration

Open Duration describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Probe Calls

Probe Calls describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Success Thresholds

Success Thresholds describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## State Transitions

State Transitions describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Dependency-Specific Breakers

Dependency-Specific Breakers describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Breaker Metrics

Breaker Metrics describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## False Positives

False Positives describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Configuration Risks

Configuration Risks describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Interaction With Retries And Timeouts

Interaction With Retries And Timeouts describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Java-Oriented Example

```java
try {
    return dependency.call(request);
} catch (TransientDependencyException ex) {
    // Retry only when the operation is safe and the request still has budget.
    throw ex;
}
```

The example is intentionally small: the important lesson is not a library choice, but the decision to classify failure before choosing retry, fallback, rejection, or recovery.
