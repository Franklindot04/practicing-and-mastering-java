# Chaos Engineering And Failure Injection

Chaos engineering is controlled learning about failure. It is not breaking real systems for spectacle, and it must use blast-radius limits, abort conditions, and approval appropriate to the environment.

## Coverage Notes

### Controlled Experiments

A chaos experiment changes one condition to test a hypothesis.

Java angle: Use safe test doubles in Java unit tests.

Tradeoff or failure case: Uncontrolled experiments are incidents.

### Steady-State Hypothesis

The steady-state hypothesis defines normal behavior expected to continue.

Java angle: Examples: success rate stays above target, queue drains, or fallback count remains bounded.

Tradeoff or failure case: Without a hypothesis, results are hard to interpret.

### Blast Radius

Blast radius limits the users, traffic, data, or time affected.

Java angle: Run local or staging experiments before production.

Tradeoff or failure case: Large radius experiments require stronger controls.

### Abort Conditions

Abort conditions say when to stop immediately.

Java angle: Examples include error rate, latency, or manual operator concern.

Tradeoff or failure case: No abort condition means no safe boundary.

### Safe Environments

Safe environments include unit tests, local simulators, staging, and tightly scoped production experiments.

Java angle: Use injected failures in Java tests.

Tradeoff or failure case: Do not attack systems you do not own or have permission to test.

### Failure Injection

Failure injection deliberately returns errors from dependencies.

Java angle: A fake client can throw configured exceptions.

Tradeoff or failure case: Injected failures should be reversible.

### Latency Injection

Latency injection delays responses to test deadlines and queues.

Java angle: Use virtual time or fake sleepers, not long real sleeps.

Tradeoff or failure case: Real sleeps make tests slow and flaky.

### Exception Injection

Exception injection verifies classification and fallback paths.

Java angle: Throw typed exceptions from test doubles.

Tradeoff or failure case: Broad catch blocks can hide unsafe behavior.

### Dependency Unavailability

Dependency unavailability tests circuit breakers and graceful degradation.

Java angle: Model unavailable inventory, payment, or notification.

Tradeoff or failure case: Critical dependencies should fail clearly.

### Resource Exhaustion Simulation

Resource exhaustion simulation fills bounded permits or queues.

Java angle: Use semaphores and small limits in tests.

Tradeoff or failure case: Do not exhaust a real shared machine.

### Instance And Partition Concepts

Instance termination and network partition are concepts for losing compute or communication.

Java angle: Represent them locally as unavailable clients or timeout failures.

Tradeoff or failure case: Avoid unsafe real-system disruption instructions.

### Production Safety

Production chaos requires approval, monitoring, aborts, and customer-impact review.

Java angle: Start small and reversible.

Tradeoff or failure case: Learning must never ignore user harm.

### Ethical And Organizational Considerations

Chaos work affects people and trust.

Java angle: Communicate scope, timing, and ownership.

Tradeoff or failure case: Surprise destructive tests are not acceptable.
