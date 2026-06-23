# Distributed Discovery And Coordination

Distributed systems need components to find each other and make shared decisions. Both problems are harder than they first appear because services can start, stop, move, slow down, or lose contact.

## Topics

- [Service Discovery](service-discovery.md)
- [Leader Election](leader-election.md)
- [Consensus Overview](consensus-overview.md)
- [Distributed Locks](distributed-locks.md)

## Learning Goals

- Explain why static hostnames are not enough for many distributed systems.
- Describe service discovery at a high level.
- Explain leader election and consensus without implementing them.
- Understand why distributed locks are dangerous when misunderstood.
- Recognize when coordination adds more complexity than value.

## Mental Model

```text
Service A needs Service B

Where is Service B?
Is it healthy?
Which instance should receive traffic?
Who decides when instances disagree?
```

Coordination is about turning uncertain, changing system state into a decision that services can safely use.

