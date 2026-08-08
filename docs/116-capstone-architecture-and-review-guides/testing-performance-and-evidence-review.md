# Testing, Performance, and Evidence Review

Tests can prove local behavior under the modeled assumptions. They cannot prove production readiness, real throughput, or long-running reliability by themselves.

## Testing Review

- Tests are deterministic, bounded, and infrastructure-independent by default.
- Scenario names map to requirements.
- Failure-path tests are as visible as success-path tests.
- Clocks, identifiers, and workload sizes are controlled.

## Performance Review

- Budgets are stated as learning constraints unless measured under representative load.
- Percentiles, regressions, and profiling evidence include methodology.
- Benchmarks and diagnostic artifacts are not committed.

## Evidence Review

Evidence should name command, environment, data size, result, limitation, and conclusion. Avoid conclusions that outrun the measurement.
