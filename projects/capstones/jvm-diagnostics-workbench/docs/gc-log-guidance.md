# GC-Log Guidance

GC logs can support allocation and retained-object investigation. Interpret them with workload size, heap settings, and JVM version. A small local workload does not predict production collector behavior.

## Diagnostic Question

Use GC logs to ask how often collections occur, how long pauses are, whether memory returns after collection, and whether allocation rate changed between baseline and candidate runs.

## Evidence To Capture

- JVM version and collector.
- Heap settings.
- Workload name and bounds.
- Total runtime and input size.
- Pause times and collection frequency.
- Before/after occupancy where available.

## Interpretation Method

Look for trends, not isolated lines. Allocation-heavy workloads may show frequent collections without a leak. Retained-object workloads may show occupancy staying high after collection. Correlate GC evidence with latency percentiles and allocation profiles.

## Cautions

GC flags and log formats vary by JVM version. Do not copy tuning flags from another application without workload evidence. A local GC log from a tiny workload is useful for learning, not for sizing production heaps.
