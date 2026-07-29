# Bulkheads And Resource Isolation

Reliability work starts by naming the behavior precisely. A vague statement such as `the service is down` is less useful than a statement about the caller, dependency, symptom, time window, and recovery expectation.

## Bulkhead Pattern

Bulkhead Pattern describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Thread-Pool Isolation

Thread-Pool Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Semaphore Isolation

Semaphore Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Queue Isolation

Queue Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Connection-Pool Isolation

Connection-Pool Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Tenant Isolation

Tenant Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Dependency Isolation

Dependency Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Bounded Queues

Bounded Queues describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Concurrency Limits

Concurrency Limits describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Starvation

Starvation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Rejection Policies

Rejection Policies describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Resource Leaks

Resource Leaks describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Blast-Radius Reduction

Blast-Radius Reduction describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Selecting Isolation Boundaries

Selecting Isolation Boundaries describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

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
