# Capstone System Design Exercise Solutions

These solutions correspond exactly to the exercises in `exercises/capstone-system-design/README.md` on the Stage 30 exercises branch. They preserve exercise numbering, titles, and order.

### Exercise 1: Review Commerce Requirements

Assume the commerce capstone is a local simulation. A good trace maps catalogue, pricing, inventory, cart, checkout, payment, order lifecycle, shipment, notification, identity boundary, search projection, messaging boundary, and observability to records, methods, tests, and docs. The trade-off is compact code versus separate packages. The limitation is that in-memory boundaries do not prove durability. An alternative is separate modules per domain, but that would add scaffolding. Java implementation should expose typed records and public methods. Testing should cover success and failure scenarios, while operations should use reports, audit entries, and runbooks.

### Exercise 2: Identify Missing Non-Functional Requirements

Assume no deployed service exists. Missing NFRs include authentication, authorization, durability, latency budget, throughput budget, availability target, recovery objective, data retention, privacy, audit retention, rate limiting, and deployment rollback. Each requires evidence such as threat models, load tests, backup drills, SLOs, or security review. The trade-off is avoiding unsupported claims while still showing design awareness. Local tests demonstrate modeled behavior, not operational readiness.

### Exercise 3: Evaluate Package and Module Boundaries

Assume the capstone remains a standalone Maven project. Returns and refunds should add boundaries near order lifecycle and payment rather than overload checkout. Catalogue and inventory should stay separate. Shared records should remain small. Alternatives include package-per-domain or module-per-domain. Package-per-domain is simpler here; module-per-domain is stronger for larger teams. Tests should assert public behavior instead of private package layout.

### Exercise 4: Review Inventory Consistency

Assume a single-process in-memory reservation model. The final unit should be reserved by the first accepted checkout and rejected for the second. This proves local contention modeling, not database isolation. A production version would need transaction boundaries, locking or optimistic concurrency, inventory reconciliation, and failure recovery. The trade-off is clarity and determinism versus realism.

### Exercise 5: Review Payment Idempotency

Assume idempotency keys are stable per caller operation. Duplicate payment requests should return the original result and avoid a second side effect. This is not exactly-once execution; it is duplicate suppression inside the modeled boundary. A real gateway needs persisted idempotency keys, timeout handling, reconciliation with provider records, and careful response caching. Tests should retry the same key and assert stable outcome.

### Exercise 6: Review Checkout Compensation

Assume payment can fail after reservation. The saga should release reservations on payment rejection or timeout and record audit evidence. If compensation fails, the system should expose that failure and require reconciliation. The trade-off is availability through sagas versus complexity from partial failure. Alternatives include preauthorization, shorter reservations, or synchronous transaction boundaries where feasible.

### Exercise 7: Analyze Message-Redelivery Safety

Assume event ids are stable. The consumer should record processed ids and return a duplicate result when redelivered. This prevents repeated projection or notification side effects in the simulation. A broker-backed system would still need durable consumer offsets, idempotent handlers, poison-message handling, and replay procedures. Avoid saying the system guarantees exactly-once delivery.

### Exercise 8: Design Reconciliation

Assume reports expose reserved units, confirmed orders, and quarantined events. Start by comparing reservations without confirmed orders, confirmed orders without downstream events, and dead letters by reason. Prioritize customer-impacting and money-impacting anomalies. The limitation is that the local report lacks real timestamps, owners, and durable records. Operations should define safe repair, replay, or manual escalation paths.

### Exercise 9: Review Workflow Leasing

Assume deterministic time. A leased task belongs to one worker until its ttl expires. After expiration, the task returns to ready state with an incremented attempt and may be leased by another worker. This is safe only if work completion is idempotent or side effects are protected. A real system would persist leases and heartbeats durably and handle clock drift.

### Exercise 10: Analyze Duplicate Task Execution

Assume completion keys represent side-effect identity. Duplicate completion with the same key should be harmless; completion from the wrong worker or without a valid lease should be rejected. The limitation is that external side effects may already have happened. Alternatives include transactional outbox, external idempotency stores, or compensating actions. Tests should cover duplicate completion and invalid lease completion.

### Exercise 11: Design Retry and Quarantine Policies

Assume transient and poison failures are distinguishable in the simulation. Retry transient failures with bounded exponential backoff and a maximum attempt count. Quarantine poison or irreversible work for operator review. The trade-off is fast recovery versus repeated harm. A production policy should include owner, alert threshold, payload inspection rules, and replay approval.

### Exercise 12: Evaluate Partition Pressure

Assume partition counts are visible. A hot partition should trigger a rebalance recommendation, not automatic scaling claims. Options include changing partition key, splitting hot tenants, adding workers for that partition, throttling noisy producers, or separating priority queues. The limitation is no real cluster balancing. Tests can verify the recommendation threshold only.

### Exercise 13: Design Admission Control

Assume admission capacity is a local budget. When capacity is exhausted, reject new work with an explicit reason and metrics rather than growing an unbounded queue. Alternatives include priority admission, per-tenant limits, backpressure, and degraded modes. The operational signal should include rejection count and capacity state. The Java implementation should keep rejection deterministic.

### Exercise 14: Review Recovery Assumptions

Assume the state log is in memory. Replay can demonstrate reasoning about stored records and duplicate side-effect protection, but it cannot prove durability across process loss. A real design needs durable append-only storage, snapshots, schema evolution, replay tooling, idempotent consumers, and recovery drills. The trade-off is teachability versus infrastructure realism.

### Exercise 15: Create Diagnostics Plan

Assume profiling is opt-in and artifacts are local. Start with symptom, workload config, JVM version, baseline, candidate run, and small evidence. Choose CPU profile, allocation view, thread dump, or GC log based on symptom. Keep durations short and sizes bounded. Clean recordings and dumps before commit. The plan should end with a reversible change and validation run.

### Exercise 16: Select Profiling Evidence

Assume synthetic workloads. CPU symptoms map to method-level CPU samples. Allocation symptoms map to allocation profiles or GC logs. Lock symptoms map to thread dumps and blocked states. Pool saturation maps to executor queue, active thread, and latency signals. One sample is weak evidence; repeated bounded samples are stronger. The Java tests should validate calculations, not profiler tools.

### Exercise 17: Define Performance Budgets

Assume deterministic fixture latencies. A budget might allow candidate p95 to be at most 1.20 times baseline p95. If baseline p95 is 100 ms and candidate p95 is 140 ms, the change fails. This does not predict production capacity. Alternatives include absolute thresholds, percentile bands, or regression budgets per workload. Report method and limitation with the result.

### Exercise 18: Evaluate Architecture Fitness Checks

Assume checks are local guardrails. They can show that a catalogue exists, messaging boundaries are exercised, dead-letter handling is reachable, and capacity checks are present. They do not prove architecture quality in general. Alternatives include static architecture tests, dependency rules, contract tests, and review checklists. Operations should treat checks as early warning, not certification.

### Exercise 19: Review Security Boundaries

Assume caller identity is pre-established. Current security coverage is boundary documentation, not implemented authentication or authorization. Needed controls include authn, authz, token validation, secrets, encryption decisions, audit retention, tenant isolation, abuse controls, and dependency review. Java implementation should not hard-code credentials. Tests should avoid personal data and focus on authorization rules only if implemented.

### Exercise 20: Review Observability

Assume local reports are the available signal. Checkout failures need result reason, inventory failure count, payment result, compensation audit, dead letters, and load shedding. Workflow failures need leases, attempts, duplicates, quarantines, and rejected admissions. A real service would add logs, metrics, traces, dashboards, SLOs, and alerts. Avoid alerting claims without thresholds.

### Exercise 21: Review Deployment Assumptions

Assume no deployment exists. Missing readiness items include packaging, environment config, secret management, auth, persistence, broker setup, migrations, observability pipeline, rollback, backups, load testing, threat review, and incident ownership. The capstones are useful designs but not deployable services as-is. A readiness assessment should mark each item missing, partial, or evidenced.

### Exercise 22: Identify Misleading Production Claims

Assume all claims must map to evidence. Replace "runs a real distributed workflow engine" with "simulates workflow leasing and replay locally." Replace "ready for production traffic" with "default tests validate bounded local scenarios." Replace "guarantees exactly-once processing" with "uses idempotency keys and duplicate detection in the simulation." Replace "complete security" with "documents identity and authorization boundaries." Replace "release is published" with "release files are prepared for later explicit release action."

### Exercise 23: Prepare a Capstone Walkthrough

Assume a ten-minute limit. Spend one minute on problem, two on boundaries, two on success path, two on failure paths, one on tests, one on operations, and one on limitations. The trade-off is depth versus coverage. Prefer one concrete scenario over a tour of every file. Connect Java records and tests to design decisions.

### Exercise 24: Produce a Release-Readiness Assessment

Assume Stage 30 PRs are open but unmerged. Check branch ownership, tests, generated artifacts, links, answer coverage, release-note consistency, completion-report methodology, no tags, no releases, and merge order. The result may be "prepared for review" rather than "released." A tag should remain a separate explicit action after merges and audit.

### Exercise 25: Propose Safe Future Improvements

Assume Stage 30 is the final planned curriculum stage. Safe future work can be framed as maintenance: improve tests, refine docs, add diagrams, update dependencies, or run optional profiling. Do not define another planned curriculum stage. Prioritize by risk, value, and effort. Each improvement should name evidence needed and avoid changing release status.
