# JFR Instructions

Use Java Flight Recorder only for opt-in local practice. Keep duration short, record to an ignored local path, and capture the command, workload size, JVM version, and observation notes separately.

## Diagnostic Question

Use JFR when you want a timeline of runtime behaviour: CPU samples, allocation activity, lock events, GC pauses, thread states, and JVM configuration. It is useful when one signal is not enough and you need correlation over time.

## Safe Practice

- Keep recording duration short.
- Use bounded workload sizes.
- Store recordings outside committed paths.
- Name recordings with workload and timestamp locally.
- Delete recordings after analysis unless there is a specific reason to keep them outside the repository.

## Interpretation Method

Look for correlation: did allocation spikes line up with latency spikes, did blocked threads appear during pool saturation, did GC pauses coincide with retained-object pressure? Treat JFR as evidence to compare with code and tests, not as automatic root-cause proof.

## Overhead And Limitations

JFR is designed for low overhead, but it still has runtime cost. Event settings, duration, workload, and JVM version matter. Local JFR evidence from this workbench should not be used to claim production capacity.
