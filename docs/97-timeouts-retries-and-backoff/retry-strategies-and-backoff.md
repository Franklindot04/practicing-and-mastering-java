# Retry Strategies And Backoff

Reliability work starts by naming the behavior precisely. A vague statement such as `the service is down` is less useful than a statement about the caller, dependency, symptom, time window, and recovery expectation.

## Immediate Retry

Immediate Retry describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Fixed Delay

Fixed Delay describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Linear Backoff

Linear Backoff describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Exponential Backoff

Exponential Backoff describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Capped Exponential Backoff

Capped Exponential Backoff describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Jitter

Jitter describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Full Jitter

Full Jitter describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Equal Jitter

Equal Jitter describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Decorrelated Jitter

Decorrelated Jitter describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Retryable Versus Non-Retryable Failures

Retryable Versus Non-Retryable Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Maximum Attempts

Maximum Attempts describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Elapsed-Time Limits

Elapsed-Time Limits describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Retry-After Hints

Retry-After Hints describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Avoiding Synchronized Retry Storms

Avoiding Synchronized Retry Storms describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

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
