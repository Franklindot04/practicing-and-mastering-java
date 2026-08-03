# Answer Key

## Multiple Choice

- MC-01: C. `LinkedHashSet` preserves insertion order and prevents duplicates; lists allow duplicates and `HashSet` does not guarantee order.
- MC-02: B. Wildcards express variance; raw types weaken safety and type erasure limits runtime generic information.
- MC-03: B. Swallowing exceptions hides failure and can leave state misleading.
- MC-04: A. `ExecutorService` manages bounded reusable worker threads.
- MC-05: B. p99 is tail latency; cause requires evidence.
- MC-06: B. Typed request records plus validation make contracts clearer.
- MC-07: B. Local tests prove modeled behavior, not deployment readiness.
- MC-08: A. Idempotency reduces duplicate side effects during retries.
- MC-09: B. A Service provides a stable endpoint for pods.
- MC-10: C. Heap dumps are generated diagnostic artifacts and may contain sensitive data.
- MC-11: A. Circuit breakers stop repeated calls to unhealthy dependencies.
- MC-12: B. Redelivery can happen, so consumers should be idempotent.

## Short Answer

- SA-01: Prefer composition when behavior can be delegated without forcing an inheritance hierarchy. It reduces coupling and avoids fragile base-class assumptions.
- SA-02: `Optional` clarifies that a value may be absent. It is misused when stored everywhere, used for required fields, or unwrapped blindly.
- SA-03: Authentication verifies identity; authorization decides what that identity may do.
- SA-04: Transactions keep related persistence changes atomic and consistent under failure.
- SA-05: Docker packages runtime assumptions but does not solve application bugs, secrets, data durability, or operational process.
- SA-06: A safe rollback plan defines trigger, artifact, data compatibility, operator, verification, and communication.
- SA-07: Logs describe events, metrics quantify behavior, traces connect request paths, and health checks expose service status.
- SA-08: An SLO is a target for user-visible reliability; alerts should signal meaningful risk to that target.
- SA-09: Networks, nodes, clocks, dependencies, and storage can fail independently, creating partial failure.
- SA-10: Transactional outbox coordinates state change and event publication; it still needs relay, idempotent consumers, and cleanup.
- SA-11: Allocation-heavy evidence can suggest GC pressure or object churn, but workload size and JVM settings matter.
- SA-12: Present it as a deterministic simulation with tests and limitations, not as a deployed system.

## Code Review

- CR-01: Risks are hidden errors and misleading empty results. A safer alternative is typed failure handling, useful context, and tests for failure behavior.
- CR-02: Thread-per-request can exhaust resources. Use a bounded `ExecutorService` or framework-managed executor.
- CR-03: Raw exception messages can leak internals and confuse clients. Return stable error contracts and log details internally.
- CR-04: Retries may charge or mutate twice. Require an idempotency key and persist the result.
- CR-05: Use deterministic clocks, latches with short timeouts, futures, polling with bounded conditions, or direct scheduler control.
- CR-06: Without warm-up and fixed data, JVM compilation, allocation, and input variation can dominate results.
- CR-07: Secrets in source can leak credentials. Use environment-specific secret stores and exclude local secret files.
- CR-08: Invalid schemas may mutate state before rejection. Validate contract first, then apply side effects.

## Architecture Scenarios

- AS-01: Catalogue owns product identity, inventory owns availability and reservations, checkout coordinates but does not own those facts.
- AS-02: HTTP is simpler for required immediate answers; events are better for decoupled shipment notification and retryable eventual consistency.
- AS-03: Store task state, worker id, lease expiration, attempts, and idempotent completion keys.
- AS-04: Cache-aside can return old values until eviction or refresh; use TTL, invalidation, versioning, or explicit stale handling.
- AS-05: Separate ownership by order, payment, inventory, customer, and notification; use contracts for cross-boundary communication.
- AS-06: Add optional fields, keep old meanings stable, version contracts, and reject incompatible versions safely.
- AS-07: Persistence, broker, auth, secrets, deployment, observability, backup, and load evidence need real infrastructure.
- AS-08: Check branch scope, tests, links, release files, counts, generated artifacts, identity exposure, no tags, and merge order.

## Failure Analysis

- FA-01: Return the stable idempotent result, avoid duplicate payment, and reconcile timeout state if external payment status is unknown.
- FA-02: Let the lease expire, increment attempts, reassign only if side effects are idempotent or guarded.
- FA-03: Quarantine after bounded attempts, preserve evidence, stop automatic replay, and require operator decision.
- FA-04: Collect rollout version, error rate, logs, events, health, recent changes, and rollback compatibility.
- FA-05: Review schema drift, permissions, data volume, locks, ordering, and rollback assumptions.
- FA-06: Tail users are worse off; investigate contention, GC, slow dependencies, or queueing.
- FA-07: Continue the core workflow if safe, record degraded status, and avoid blocking on optional work.
- FA-08: Correct release files to say tag creation is pending and do not fabricate release status.

## Testing and Performance

- TP-01: Use a deterministic clock and assert calculated delays and attempt counts without sleeping.
- TP-02: Use test doubles for local behavior and rare failures; use integration tests for real boundaries and configuration.
- TP-03: Cover successful checkout, validation, inventory contention, payment failures, idempotency, compensation, messages, and reconciliation.
- TP-04: Coverage shows executed lines or branches; it does not prove assertions, correctness, or risk coverage.
- TP-05: Example: candidate p95 must be within 1.20 times baseline p95; document inputs and limitation.
- TP-06: CPU profile for hot methods, allocation profile or GC logs for object churn, thread dumps for lock contention.
- TP-07: Opt-in bounded workloads prevent accidental slow tests, memory growth, and unsafe diagnostic artifacts.
- TP-08: Remove target directories, recordings, dumps, logs, benchmark outputs, and temporary notes before commit.

## Operational Readiness

- OR-01: Health, error rate, latency, throughput, saturation, dependency status, logs, and deployment version.
- OR-02: Identify event, reason, owner, blast radius, replay safety, repair action, and closure evidence.
- OR-03: Liveness says process should be restarted; readiness says whether it should receive traffic.
- OR-04: Secrets need external storage, rotation, least privilege, audit, and no source control exposure.
- OR-05: Deployment evidence needs packaging, config, security, persistence, observability, rollback, load, and ownership.
- OR-06: IaC state can expose secrets or drift; protect with locking, access control, backups, and review.
- OR-07: Mesh features can add traffic policy and telemetry, but application timeouts and idempotency still matter.
- OR-08: Verify merges, tests, links, release notes, version consistency, clean worktree, and explicit authorization before tagging.
