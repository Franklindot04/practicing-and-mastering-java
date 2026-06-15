# Application Vs Infrastructure Architecture

Application architecture and infrastructure architecture overlap, but they answer different questions.

## Application Architecture

Application architecture focuses on code and boundaries:

- Controllers, services, repositories, and DTOs.
- Validation and error handling.
- Synchronous and asynchronous workflows.
- Domain rules and persistence boundaries.
- How code remains testable.

## Infrastructure Architecture

Infrastructure architecture focuses on runtime environment:

- Compute instances or containers.
- Load balancers and network paths.
- Databases, queues, caches, and object storage.
- Secrets and configuration delivery.
- Monitoring, scaling, recovery, and cost controls.

## How They Meet

A stateless Java backend is easier to scale horizontally because any instance can handle any request. A backend that stores session state in memory may need sticky routing or redesign. The application choice shapes the infrastructure choice.

Architecture review should look at both sides together.

