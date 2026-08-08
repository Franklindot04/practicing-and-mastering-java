# JVM Diagnostics and Reliability Workbench

This capstone provides bounded Java workloads for practicing JVM and reliability diagnostics safely. The default test suite validates behavior and safety without running profilers or generating recordings.

```bash
mvn test
```

Default tests are fast, bounded, local-only, and infrastructure-independent.

## Safety Rules

- No permanent deadlock
- No infinite loop
- No unbounded memory growth
- No uncontrolled thread creation
- Workloads are opt-in outside tests
- Profiling tools are optional
- Diagnostic files must be cleaned after use

## Documentation

- [Workload catalogue](docs/workload-catalogue.md)
- [Profiling commands](docs/profiling-commands.md)
- [JFR instructions](docs/jfr-instructions.md)
- [Thread-dump instructions](docs/thread-dump-instructions.md)
- [Heap-dump cautions](docs/heap-dump-cautions.md)
- [GC-log guidance](docs/gc-log-guidance.md)
- [Expected evidence](docs/expected-evidence.md)
- [Interpretation limitations](docs/interpretation-limitations.md)
- [Artifact cleanup](docs/artifact-cleanup.md)
- [Incident runbook](docs/incident-runbook.md)
- [Safety and production comparison](docs/safety-and-production-comparison.md)
