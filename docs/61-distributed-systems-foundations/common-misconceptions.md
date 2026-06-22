# Common Misconceptions

Distributed systems often sound cleaner than they feel in production. These misconceptions are worth challenging early.

## "Microservices Are Always Better"

Microservices can help with team autonomy and scaling, but they also add deployment, testing, monitoring, and failure-handling work.

A modular monolith can be a better starting point when the product, team boundaries, and scaling needs are still changing.

## "The Network Is Reliable"

Networks lose packets, slow down, partition, and behave differently under load. Distributed designs must assume calls can fail or become slow.

## "Retries Fix Failures"

Retries can help with temporary failures. They can also multiply load, repeat side effects, and turn a small outage into a larger one.

Safe retries need timeouts, limits, backoff, and idempotency.

## "The Database Is The Only State"

State can also live in caches, queues, search indexes, sessions, logs, and downstream services. Understanding where state lives helps explain consistency and recovery behavior.

## "More Services Means More Reliability"

More services can isolate some failures, but they also create more dependencies. Reliability comes from clear ownership, good defaults, observability, and practiced operations.

## "Distributed Systems Are Only For Big Companies"

Even a small backend that calls a payment API, sends email, and writes to a remote database has distributed-system concerns.

