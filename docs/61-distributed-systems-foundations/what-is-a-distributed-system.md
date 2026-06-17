# What Is A Distributed System

A distributed system is a set of separate components that communicate over a network and work together as one system from the user's point of view.

Examples can include:

- A web app calling a backend API.
- A backend API calling another internal service.
- A service writing to a database hosted on another machine.
- A background worker processing tasks created by an API.
- A cache, search index, or message broker supporting application behavior.

## Monolith Vs Distributed Systems

A monolith keeps most application behavior in one deployable unit. It can still be well-structured internally with packages, modules, services, and clear boundaries.

A distributed system moves some behavior into separate deployable units that communicate over a network.

```text
Monolith:

Client -> One application -> Database

Distributed:

Client -> API service -> Order service -> Payment service
                    \-> Inventory service
```

## Why Teams Split Systems

Teams may choose distribution to support independent scaling, separate ownership, fault isolation, technology boundaries, or organizational growth.

Those benefits are not free. Distribution adds latency, failure modes, operational work, observability needs, and harder testing.

## A Healthy Starting Rule

Start with clear boundaries in your code and data model. Move a boundary across the network only when the benefits are worth the extra complexity.

