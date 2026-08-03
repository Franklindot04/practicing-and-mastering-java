# Capstone System Design Exercises

Use these exercises to review the Stage 30 capstones from a system-design, testing, operations, and portfolio-readiness perspective. This directory intentionally contains exercises only; corresponding solutions live in a separate Stage 30 branch.

## Exercise Format

Each exercise includes objective, scenario, requirements, constraints, assumptions, expected reasoning, acceptance criteria, and optional extension.

## Exercises

### Exercise 1: Review Commerce Requirements

- Objective: Verify that the commerce capstone requirements are complete and traceable.
- Scenario: A reviewer asks whether catalogue, pricing, inventory, checkout, payment, order lifecycle, shipment, notification, search, messaging, security boundary, and observability are all represented.
- Requirements: Map every required business area to code, tests, or docs.
- Constraints: Do not claim deployment evidence.
- Assumptions: The capstone remains local and deterministic.
- Expected reasoning: Distinguish implemented behavior from simulated boundary.
- Acceptance criteria: Produce a traceability table with gaps and limitations.
- Optional extension: Add risk priority for each gap.

### Exercise 2: Identify Missing Non-Functional Requirements

- Objective: Find unspoken reliability, security, performance, and operability expectations.
- Scenario: The README explains local behavior but a stakeholder asks about real traffic.
- Requirements: Identify missing NFRs and evidence required for each.
- Constraints: Avoid adding vague quality claims.
- Assumptions: No real production deployment exists.
- Expected reasoning: Connect each NFR to measurable evidence.
- Acceptance criteria: List at least eight NFRs with evidence and current status.

### Exercise 3: Evaluate Package and Module Boundaries

- Objective: Review whether capstone package boundaries match domain responsibilities.
- Scenario: A change request adds returns and refunds.
- Requirements: Identify which boundaries should change and which should stay stable.
- Constraints: Preserve controlled shared-kernel usage.
- Assumptions: The project remains a standalone Maven module.
- Expected reasoning: Explain coupling, ownership, and public interfaces.
- Acceptance criteria: Provide a boundary review with at least three improvement options.

### Exercise 4: Review Inventory Consistency

- Objective: Analyze reservation consistency and contention handling.
- Scenario: Two customers attempt to reserve the final unit.
- Requirements: Explain the modeled consistency rule and failure behavior.
- Constraints: Do not assume real database locks.
- Assumptions: In-memory state is single-process.
- Expected reasoning: Separate simulation behavior from durable concurrency control.
- Acceptance criteria: Describe success, rejection, and production gap.

### Exercise 5: Review Payment Idempotency

- Objective: Determine whether duplicate payment requests are handled safely.
- Scenario: A client retries checkout after a network timeout.
- Requirements: Trace idempotency key usage and stable result behavior.
- Constraints: Avoid exactly-once claims.
- Assumptions: Payment state is local to the simulation.
- Expected reasoning: Discuss duplicate detection, side effects, and external gateway requirements.
- Acceptance criteria: Identify what tests prove and what real integration would need.

### Exercise 6: Review Checkout Compensation

- Objective: Evaluate compensation after payment failure.
- Scenario: Inventory is reserved, then payment is rejected.
- Requirements: Explain compensation path, audit evidence, and compensation failure.
- Constraints: Do not hide failed compensation.
- Assumptions: Compensation is local and immediate.
- Expected reasoning: Discuss saga trade-offs and reconciliation.
- Acceptance criteria: Provide a failure tree and operator action list.

### Exercise 7: Analyze Message-Redelivery Safety

- Objective: Check duplicate-message protection.
- Scenario: An order event is delivered twice after restart.
- Requirements: Explain event identifiers, consumer state, and duplicate result.
- Constraints: No real broker is present.
- Assumptions: Redelivery is simulated by calling the consumer twice.
- Expected reasoning: Avoid exactly-once language.
- Acceptance criteria: Name the idempotency mechanism and broker-era gaps.

### Exercise 8: Design Reconciliation

- Objective: Define a reconciliation workflow for orders, reservations, and dead letters.
- Scenario: Operators see confirmed orders and quarantined events.
- Requirements: Compare reserved units, confirmed orders, and dead-letter counts.
- Constraints: Keep the runbook bounded.
- Assumptions: Reports are generated locally.
- Expected reasoning: Prioritize anomalies and corrective action.
- Acceptance criteria: Produce a reconciliation checklist.

### Exercise 9: Review Workflow Leasing

- Objective: Evaluate task lease ownership and expiration.
- Scenario: A worker leases a task and then stops heartbeating.
- Requirements: Explain lease ttl, reassignment, and attempt count.
- Constraints: Avoid sleeping tests.
- Assumptions: Time is deterministic.
- Expected reasoning: Show why reassignment is safe only with idempotent work.
- Acceptance criteria: Provide lease-state transitions.

### Exercise 10: Analyze Duplicate Task Execution

- Objective: Identify duplicate completion and side-effect risks.
- Scenario: A worker sends completion twice.
- Requirements: Trace completion key handling.
- Constraints: Do not present idempotency as universal correctness.
- Assumptions: Side effects are represented locally.
- Expected reasoning: Discuss external side-effect stores.
- Acceptance criteria: List safe and unsafe duplicate cases.

### Exercise 11: Design Retry and Quarantine Policies

- Objective: Create a retry, backoff, and dead-letter policy.
- Scenario: Some tasks fail transiently and one task is poison.
- Requirements: Define retry limits, backoff, quarantine triggers, and operator review.
- Constraints: Keep retries bounded.
- Assumptions: Poison detection is explicit.
- Expected reasoning: Balance availability and repeated harm.
- Acceptance criteria: Provide a policy table.

### Exercise 12: Evaluate Partition Pressure

- Objective: Review hot partition detection.
- Scenario: Most tasks route to one partition.
- Requirements: Interpret task counts and rebalance recommendations.
- Constraints: Do not claim automatic cluster scaling.
- Assumptions: Partition ownership is simulated.
- Expected reasoning: Discuss routing, sharding, and capacity.
- Acceptance criteria: Recommend at least three mitigation options.

### Exercise 13: Design Admission Control

- Objective: Prevent unbounded queues under pressure.
- Scenario: The workflow service reaches its admission capacity.
- Requirements: Define rejection behavior and user-facing signal.
- Constraints: Avoid silent drops.
- Assumptions: Capacity is a local budget.
- Expected reasoning: Explain why bounded rejection can be safer than unbounded acceptance.
- Acceptance criteria: Provide an admission-control decision tree.

### Exercise 14: Review Recovery Assumptions

- Objective: Evaluate durable-style state-log replay.
- Scenario: The platform restarts after task completion.
- Requirements: Explain what replay can reconstruct and what it cannot.
- Constraints: No real durable log exists.
- Assumptions: The state log is in memory.
- Expected reasoning: Separate replay reasoning from durability evidence.
- Acceptance criteria: List production storage requirements.

### Exercise 15: Create Diagnostics Plan

- Objective: Build a safe JVM diagnostic plan.
- Scenario: A local workload shows latency regression.
- Requirements: Choose evidence, commands, and cleanup.
- Constraints: No unbounded workload or committed artifacts.
- Assumptions: Profiling is opt-in.
- Expected reasoning: Start with minimal evidence and bounded reproduction.
- Acceptance criteria: Provide a step-by-step plan.

### Exercise 16: Select Profiling Evidence

- Objective: Decide which evidence fits CPU, allocation, lock, and pool symptoms.
- Scenario: Four symptoms appear in different runs.
- Requirements: Match each symptom to evidence type.
- Constraints: Avoid overinterpreting one sample.
- Assumptions: Workloads are synthetic.
- Expected reasoning: Explain why each signal is relevant.
- Acceptance criteria: Produce a symptom-to-evidence matrix.

### Exercise 17: Define Performance Budgets

- Objective: Set honest local performance budgets.
- Scenario: A candidate change worsens p95 latency.
- Requirements: Define baseline, candidate, ratio, and pass/fail threshold.
- Constraints: Do not extrapolate to production capacity.
- Assumptions: Latencies are deterministic fixture values.
- Expected reasoning: Discuss percentile interpretation.
- Acceptance criteria: State budget, result, and limitation.

### Exercise 18: Evaluate Architecture Fitness Checks

- Objective: Review fitness checks as guardrails.
- Scenario: A capstone exposes a report saying architecture checks pass.
- Requirements: Identify what each check proves.
- Constraints: Avoid broad quality claims.
- Assumptions: Checks are local assertions.
- Expected reasoning: Distinguish guardrails from formal verification.
- Acceptance criteria: Classify checks by risk reduced.

### Exercise 19: Review Security Boundaries

- Objective: Identify trust boundaries and missing controls.
- Scenario: A reviewer asks whether identity and authorization are complete.
- Requirements: Name current boundary and production controls needed.
- Constraints: Do not invent implemented auth.
- Assumptions: Caller identity is pre-established.
- Expected reasoning: Discuss authn, authz, secrets, audit, and abuse controls.
- Acceptance criteria: Provide a security gap list.

### Exercise 20: Review Observability

- Objective: Evaluate metrics, audit, reports, and runbooks.
- Scenario: A failure occurs during checkout or task execution.
- Requirements: Identify signals needed to diagnose it.
- Constraints: Avoid alerting claims without SLOs.
- Assumptions: Signals are local reports.
- Expected reasoning: Connect symptom to evidence.
- Acceptance criteria: Produce a diagnostic signal map.

### Exercise 21: Review Deployment Assumptions

- Objective: Separate local design from deployment readiness.
- Scenario: Someone wants to deploy the capstones as-is.
- Requirements: Identify missing deployment, security, operations, and load evidence.
- Constraints: Avoid unsupported launch claims.
- Assumptions: No deployment exists.
- Expected reasoning: Explain what would be required before deployment.
- Acceptance criteria: Create a readiness gap assessment.

### Exercise 22: Identify Misleading Production Claims

- Objective: Rewrite risky portfolio statements.
- Scenario: A project summary overstates the capstones.
- Requirements: Replace exaggerated claims with evidence-backed language.
- Constraints: Do not use inflated terminology.
- Assumptions: The work is a local simulation.
- Expected reasoning: Tie claims to tests, docs, and limitations.
- Acceptance criteria: Rewrite five claims.

### Exercise 23: Prepare a Capstone Walkthrough

- Objective: Build a concise interview walkthrough.
- Scenario: You have ten minutes to present one capstone.
- Requirements: Include problem, boundaries, success path, failure path, tests, operations, and limitations.
- Constraints: Do not read every file.
- Assumptions: Audience knows Java basics.
- Expected reasoning: Prioritize signal over breadth.
- Acceptance criteria: Produce a timed outline.

### Exercise 24: Produce a Release-Readiness Assessment

- Objective: Evaluate whether release preparation is complete.
- Scenario: Stage 30 branches are ready for review but not merged.
- Requirements: Check tests, links, release notes, completion report, tag status, and merge order.
- Constraints: Do not create tags or releases.
- Assumptions: Final release is a later explicit action.
- Expected reasoning: Separate preparation from release execution.
- Acceptance criteria: Produce a go/no-go checklist.

### Exercise 25: Propose Safe Future Improvements

- Objective: Suggest improvements without introducing a new planned stage.
- Scenario: A maintainer asks what could be improved after the final planned stage.
- Requirements: Name optional maintenance ideas and evidence needed.
- Constraints: Do not introduce another planned curriculum stage.
- Assumptions: Stage 30 is final planned curriculum work.
- Expected reasoning: Separate maintenance from roadmap expansion.
- Acceptance criteria: List improvements by risk, value, and effort.
