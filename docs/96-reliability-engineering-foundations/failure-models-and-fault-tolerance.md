# Failure Models And Fault Tolerance

Reliability work starts by naming the behavior precisely. A vague statement such as `the service is down` is less useful than a statement about the caller, dependency, symptom, time window, and recovery expectation.

## Transient Failures

Transient Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Persistent Failures

Persistent Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Intermittent Failures

Intermittent Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Partial Failures

Partial Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Fail-Stop Behavior

Fail-Stop Behavior describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Omission Failures

Omission Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Timing Failures

Timing Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Dependency Failures

Dependency Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Resource Exhaustion

Resource Exhaustion describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Cascading Failures

Cascading Failures describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Fault Versus Error Versus Failure

Fault Versus Error Versus Failure describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Fault Containment

Fault Containment describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Redundancy

Redundancy describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Isolation

Isolation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Graceful Recovery

Graceful Recovery describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Fail-Fast Versus Fail-Safe Behavior

Fail-Fast Versus Fail-Safe Behavior describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

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
