# Profiling Commands

Profiler execution is optional. Start with `mvn test` for safety validation. For manual profiling, run a small Java entry point or test under your chosen profiler and keep workload sizes within documented bounds.

Do not commit profiler recordings, benchmark output, heap dumps, thread dumps, GC logs, or temporary diagnostics.

## Purpose

Profiling should answer a question: where is CPU time spent, where are allocations created, which threads are blocked, or whether a candidate change regressed latency. Start with the question before choosing a tool.

## Prerequisites

- Run `mvn test` first to confirm workload bounds.
- Record Java version, Maven version, workload config, command, and machine context.
- Keep profiler output in an ignored temporary path.
- Prefer short runs and repeatable inputs.

## Command Shape

The exact profiler command depends on the tool installed locally. A useful command record includes:

```text
tool=<profiler>
java=<version>
workload=<name>
iterations=<bounded value>
allocationBytes=<bounded value>
threads=<bounded value>
duration=<short local run>
output=<temporary ignored path>
```

## Interpretation

Profiler output suggests where to investigate; it does not prove causation by itself. Correlate samples with workload configuration, latency results, logs, metrics, and code changes. If a profile contradicts other evidence, collect another bounded sample rather than forcing the conclusion.

## Overhead And Risk

Profilers can change timing, allocation patterns, and thread scheduling. Some modes require elevated local permissions. Never copy commands from another environment without checking workload size, JVM version, and output path.
