# Chaos Engineering And Failure Injection

Reliability work starts by naming the behavior precisely. A vague statement such as `the service is down` is less useful than a statement about the caller, dependency, symptom, time window, and recovery expectation.

## Chaos Engineering

Chaos Engineering describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Controlled Experiments

Controlled Experiments describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Steady-State Hypothesis

Steady-State Hypothesis describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Blast-Radius Control

Blast-Radius Control describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Abort Conditions

Abort Conditions describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Safe Environments

Safe Environments describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Failure Injection

Failure Injection describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Latency Injection

Latency Injection describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Exception Injection

Exception Injection describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Dependency Unavailability

Dependency Unavailability describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Resource Exhaustion Simulation

Resource Exhaustion Simulation describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Instance Termination Concepts

Instance Termination Concepts describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Network-Partition Concepts

Network-Partition Concepts describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Production Safety

Production Safety describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Ethical And Organizational Considerations

Ethical And Organizational Considerations describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

Practical questions:

- What caller observes this behavior?
- Is the failure transient, persistent, partial, or caused by overload?
- Does retrying make the system safer, or does it amplify pressure?
- What signal would confirm recovery?

## Learning Rather Than Destruction

Learning Rather Than Destruction describes a reliability concern that should be tied to observable evidence, caller impact, and a recovery decision. In Java systems, look for where the behavior appears in method boundaries, thread pools, network clients, persistence code, and exception handling.

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
