# Java Quality And Performance Examples

A standalone Stage 29 Maven example module for deterministic tests, test doubles, property-style invariant checks, mutation and architecture quality simulation, JMH benchmarking, and bounded profiling workloads.

## Default Tests

```bash
mvn test
```

Default tests are deterministic, infrastructure-independent, and do not run JMH or profiling workloads.

## Benchmark Compilation And Execution

Compile benchmark sources without running them:

```bash
mvn -Pbenchmarks test-compile
```

Run a short demonstration benchmark explicitly:

```bash
mvn -Pbenchmarks exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.BenchmarkLauncher
```

JMH results are machine- and workload-specific. They are not production performance claims.

## Profiling Workloads

Run bounded workloads explicitly:

```bash
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.ProfilingWorkloads -Dexec.args=cpu
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.ProfilingWorkloads -Dexec.args=allocation
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.ProfilingWorkloads -Dexec.args=lock
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.ProfilingWorkloads -Dexec.args=blocked
mvn -Pprofiling exec:java -Dexec.mainClass=dev.franklindot04.learnjava.qualityperformance.ProfilingWorkloads -Dexec.args=retained
```

Use tools such as `jcmd <pid> Thread.print`, JFR, or a sampling profiler while the bounded workload runs. Do not commit dumps, recordings, logs, or benchmark outputs.
