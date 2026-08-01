# Distributed Systems And Partial Failure

A single Java process has one heap, one set of threads, one local clock view, and one failure boundary. A distributed system has several independent processes. Each process can run, pause, restart, observe different inputs, and make progress at a different time.

Distributed systems are created for many reasons: scaling read or write volume, separating business capabilities, isolating failures, placing work closer to users, enabling independent deployments, or integrating with systems owned by other teams. Those benefits are real, but they trade simple local reasoning for networked reasoning.

## Independent Nodes

A node is an independently executing process or host. Two Java services can both be healthy from their own perspective while failing to communicate with each other. A thread dump from one JVM cannot prove the state of another JVM.

```java
record NodeId(String value) { }

enum NodeView {
    HEALTHY,
    SUSPECTED,
    UNREACHABLE
}
```

`SUSPECTED` is a better word than `DEAD` when a node is judged through network communication. A caller usually knows only that it did not receive a response before its deadline.

## Partial Failure

Partial failure means one part of the system fails while another part continues. Examples:

- the payment service commits a charge, but the order service times out before receiving the response
- one JVM is paused by garbage collection while peers continue sending requests
- a replica accepts writes but cannot replicate them because the network path is broken
- a load balancer routes to old instances during a rolling deployment

In a local method call, an exception usually means the callee did not return normally. Across a network, a timeout means the caller does not know the outcome.

```java
PaymentResult result = paymentClient.charge(request);
```

If this call times out, the safe question is not "did it fail?" The safe question is "what evidence tells us whether the remote side committed, rejected, or never received it?"

## Local Versus Distributed Guarantees

Within one JVM, `synchronized`, transactions over one local resource, and in-memory collections can provide strong local guarantees. Across nodes, those guarantees require communication, shared protocols, and failure handling.

Local guarantees:

- a method returns only after its current stack completes
- a lock protects memory visible to threads in the same JVM
- a database transaction can atomically change rows in one transactional boundary

Distributed complications:

- a caller can time out after the callee commits
- a lock service can grant a lease that expires while the old holder still runs
- two services can observe different versions of state

## Fault Domains

A fault domain is a boundary within which one failure can affect many components. A single JVM is one domain. A host, rack, network zone, database cluster, identity provider, DNS provider, and deploy pipeline can each become a shared failure domain.

Distributed design asks which failures should remain isolated. Splitting one Java application into five services does not improve availability if all five depend on the same unavailable database table, secret, or network route.

## Availability Versus Correctness

Availability means the system continues serving useful responses. Correctness means the response respects the required business invariants. During failure, these goals can conflict.

For example, a shopping cart might remain available with stale recommendations. A banking transfer should usually reject or delay ambiguous operations rather than risk double movement of money.

## Review Questions

1. Why is a timeout an unknown outcome rather than proof of failure?
2. Give one example of a local Java guarantee that does not automatically extend across services.
3. What fault domain might remain shared after a monolith is split into services?
4. When should correctness be preferred over availability?
