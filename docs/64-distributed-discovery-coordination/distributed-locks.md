# Distributed Locks

A distributed lock tries to ensure that only one participant performs a protected action at a time.

Distributed locks are tempting because they resemble local locks. They are much harder because lock ownership depends on time, network communication, and failure detection.

## Local Lock Vs Distributed Lock

```text
Local lock:
One process, shared memory, fast ownership checks.

Distributed lock:
Many processes, network calls, timeouts, leases, failure uncertainty.
```

## Lease-Based Thinking

Many distributed locks use leases. A participant owns the lock for a limited time and must renew it.

This raises questions:

- What if the owner pauses longer than the lease?
- What if renewal succeeds but the response is lost?
- What if another participant receives the lock while the old owner keeps working?

## Fencing Tokens

A fencing token is a monotonically increasing value given with lock ownership. Downstream systems can reject older tokens.

```text
Worker A gets token 10
Worker B later gets token 11
Storage accepts token 11 and rejects late writes from token 10
```

This is safer than trusting the lock alone.

## Caution

Use distributed locks sparingly. If a workflow can be redesigned with idempotency, ownership records, queues, or single-writer boundaries, that may be easier to reason about.

