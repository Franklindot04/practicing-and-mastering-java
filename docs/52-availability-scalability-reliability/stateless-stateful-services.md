# Stateless And Stateful Services

## Stateless Services

A stateless service does not depend on local in-memory state to handle the next request. Any healthy instance can process a request if it has access to required external data and configuration.

Stateless Java backends are easier to scale horizontally behind a load balancer.

## Stateful Services

A stateful service keeps important state inside the service instance or on attached storage. Databases, queues, and caches are often stateful.

Stateful systems need stronger backup, recovery, consistency, and failover planning.

## Multi-Instance Backends

Running multiple backend instances can improve availability and capacity, but only when the application is ready:

- Session state is not trapped in one instance.
- Database connections are pooled and limited.
- Background jobs do not run accidentally on every instance.
- Health checks remove unhealthy instances.

