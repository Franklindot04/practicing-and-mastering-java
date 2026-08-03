# Java Quality And Performance Engineering Lab

A controlled Stage 29 Java service/workload simulator for testing strategy, quality gates, measurement, profiling, and regression checks. It is bounded, deterministic under default tests, and does not require external infrastructure.

## Default Validation

```bash
mvn test
```

Default tests do not run JMH benchmarks, profiling workloads, Docker, databases, brokers, external APIs, or network calls.

## Optional Commands

Benchmark source compilation:

```bash
mvn -Pbenchmarks test-compile
```

Short benchmark execution, explicitly opt-in:

```bash
mvn -Pbenchmarks exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualitylab.LabBenchmarkLauncher
```

Bounded profiling workload:

```bash
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualitylab.DiagnosticScenario -Dexec.args=allocation
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualitylab.DiagnosticScenario -Dexec.args=contention
```

## Documentation

- [Test Strategy](docs/test-strategy.md)
- [Risk Matrix](docs/risk-matrix.md)
- [Quality-Gate Design](docs/quality-gate-design.md)
- [Workload Profiles](docs/workload-profiles.md)
- [Benchmark Instructions](docs/benchmark-instructions.md)
- [Profiling Instructions](docs/profiling-instructions.md)
- [Diagnostic Runbook](docs/diagnostic-runbook.md)
- [Performance Budgets](docs/performance-budgets.md)
- [Optimization Decisions](docs/optimization-decisions.md)
- [Limitations And Production Comparison](docs/limitations-and-production-comparison.md)
