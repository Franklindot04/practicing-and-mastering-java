package dev.franklindot04.learnjava.qualitylab;

import java.time.Instant;
import java.util.List;

record WorkloadProfile(String name, int requests, int concurrency, long seed) {}
record WorkloadScenario(WorkloadProfile profile, boolean cpuHeavy, boolean allocationHeavy, boolean contention) {}
record Request(String id, String sku, int quantity) {}
record Response(String id, boolean accepted, long latencyMicros, String message) {}
record Contract(String name, List<String> requiredFields, int version) {}
record DiagnosticReport(String scenario, String evidence, boolean bounded) {}
record OptimizationDecision(String hypothesis, long beforeMicros, long afterMicros, String tradeoff) {}
record TimedEvent(String name, Instant at) {}
