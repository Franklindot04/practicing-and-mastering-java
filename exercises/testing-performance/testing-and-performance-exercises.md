# Testing And Performance Exercises

## 01. Risk-Based Test Strategy

**Objective:** Practice risk-based test strategy using Java-oriented evidence.

**Scenario:** Map business and technical risks to test evidence for a payment-like Java workflow.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 02. Test Level Selection

**Objective:** Practice test level selection using Java-oriented evidence.

**Scenario:** Choose unit, component, integration, contract, E2E, smoke, and exploratory tests for a feature.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 03. Over-Mocking Review

**Objective:** Practice over-mocking review using Java-oriented evidence.

**Scenario:** Identify mocks that make tests brittle and propose fakes or state-based assertions.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 04. Deterministic Time And IDs

**Objective:** Practice deterministic time and ids using Java-oriented evidence.

**Scenario:** Refactor tests to inject Clock and identifier generators.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 05. Flaky Test Repair

**Objective:** Practice flaky test repair using Java-oriented evidence.

**Scenario:** Replace timing and shared-state instability with deterministic synchronization.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 06. Integration Boundary Design

**Objective:** Practice integration boundary design using Java-oriented evidence.

**Scenario:** Decide which adapters require real boundary tests and which can use fakes.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 07. Contract Compatibility

**Objective:** Practice contract compatibility using Java-oriented evidence.

**Scenario:** Write a contract test for compatible and breaking API or event schema changes.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 08. Property-Based Invariants

**Objective:** Practice property-based invariants using Java-oriented evidence.

**Scenario:** Design generated-input tests with a reproducible seed and clear failure report.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 09. Mutation Result Interpretation

**Objective:** Practice mutation result interpretation using Java-oriented evidence.

**Scenario:** Explain survived mutations and strengthen weak assertions.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 10. Architecture Quality Rules

**Objective:** Practice architecture quality rules using Java-oriented evidence.

**Scenario:** Design rules that protect package and interface boundaries.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 11. Coverage And Gate Assessment

**Objective:** Practice coverage and gate assessment using Java-oriented evidence.

**Scenario:** Evaluate line, branch, condition, mutation, and assertion quality evidence.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 12. Performance Requirements

**Objective:** Practice performance requirements using Java-oriented evidence.

**Scenario:** Define latency, throughput, utilization, saturation, and tail goals.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 13. Workload Profiles

**Objective:** Practice workload profiles using Java-oriented evidence.

**Scenario:** Model representative, peak, spike, stress, and soak workloads.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 14. Benchmark Smell Hunt

**Objective:** Practice benchmark smell hunt using Java-oriented evidence.

**Scenario:** Find dead-code elimination, constant folding, warm-up, and data-shape mistakes.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 15. JMH Benchmark Design

**Objective:** Practice jmh benchmark design using Java-oriented evidence.

**Scenario:** Specify state, modes, forks, warm-up, iterations, Blackhole, and allocation measurement.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 16. Latency Percentiles

**Objective:** Practice latency percentiles using Java-oriented evidence.

**Scenario:** Interpret p50, p95, p99, coordinated omission, and uncertainty.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 17. CPU Bottleneck Diagnosis

**Objective:** Practice cpu bottleneck diagnosis using Java-oriented evidence.

**Scenario:** Use profile evidence to form and test an optimization hypothesis.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 18. Allocation Pressure Diagnosis

**Objective:** Practice allocation pressure diagnosis using Java-oriented evidence.

**Scenario:** Measure object churn, retained memory, and allocation-focused improvements.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 19. Thread Dump Inspection

**Objective:** Practice thread dump inspection using Java-oriented evidence.

**Scenario:** Interpret RUNNABLE, BLOCKED, WAITING, pool exhaustion, and deadlock suspicion.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 20. Retained Memory Evidence

**Objective:** Practice retained memory evidence using Java-oriented evidence.

**Scenario:** Analyze heap-dump retained size and dominator-tree clues.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 21. GC Evidence Analysis

**Objective:** Practice gc evidence analysis using Java-oriented evidence.

**Scenario:** Interpret allocation rate, promotion, pauses, heap occupancy, and collector trade-offs.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 22. Profiling Tool Choice

**Objective:** Practice profiling tool choice using Java-oriented evidence.

**Scenario:** Choose CPU, wall-clock, allocation, lock, JFR, and native-memory tools.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 23. Lock Contention Diagnosis

**Objective:** Practice lock contention diagnosis using Java-oriented evidence.

**Scenario:** Measure bounded contention and compare synchronization alternatives.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 24. Executor Sizing

**Objective:** Practice executor sizing using Java-oriented evidence.

**Scenario:** Reason about CPU-bound, blocking, queue sizing, saturation, and backpressure.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 25. Performance Budgets

**Objective:** Practice performance budgets using Java-oriented evidence.

**Scenario:** Create p95, throughput, allocation, and failure budgets with uncertainty.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 26. Regression Prevention

**Objective:** Practice regression prevention using Java-oriented evidence.

**Scenario:** Design a gate that fails clearly when a simulated regression exceeds budget.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 27. Optimization Decision Record

**Objective:** Practice optimization decision record using Java-oriented evidence.

**Scenario:** Document hypothesis, baseline, change, result, trade-off, rollback, and risk.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 28. Production Diagnostic Runbook

**Objective:** Practice production diagnostic runbook using Java-oriented evidence.

**Scenario:** Write safe evidence-gathering steps for high CPU, memory growth, GC pauses, and latency regression.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 29. Failure Injection Boundaries

**Objective:** Practice failure injection boundaries using Java-oriented evidence.

**Scenario:** Design bounded fault-injection tests without making default tests flaky.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

## 30. Quality Strategy Review

**Objective:** Practice quality strategy review using Java-oriented evidence.

**Scenario:** Combine testing, quality gates, profiling, and performance budgets into a release recommendation.

**Requirements:** Identify the risk, the evidence you need, and the smallest reliable test or measurement that reduces uncertainty. Include Java-specific boundaries, data, and diagnostics where relevant.

**Constraints:** Keep default validation deterministic, infrastructure-independent, bounded, and free from long sleeps, unseeded randomness, generated profiler artifacts, benchmark output commits, or production-readiness claims based only on tests.

**Assumptions:** State workload, environment, data-shape, dependency, and ownership assumptions explicitly.

**Expected Reasoning:** Explain why the chosen level or tool is appropriate, what uncertainty remains, and which misleading metric or shortcut you avoided.

**Acceptance Criteria:** The answer names the test level or diagnostic evidence, includes at least one reproducibility detail, defines a clear pass/fail or investigation outcome, and documents limitations.

**Optional Extension:** Add a riskier variant, such as concurrency, schema evolution, cache staleness, GC evidence, or regression gating, while keeping it opt-in and bounded.

