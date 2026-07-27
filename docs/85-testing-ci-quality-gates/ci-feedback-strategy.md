# CI Feedback Strategy

Good CI strategy separates fast feedback from realistic feedback. Developers need quick answers while reviewers and release owners need broader evidence.

## Test Categorization

| Category | Example | CI placement |
| --- | --- | --- |
| Unit | Pure domain logic. | Every local and PR run. |
| Component | Service with fakes. | PR run. |
| Integration | Repository or adapter boundary. | PR or scheduled, depending on cost. |
| Contract | Consumer-provider compatibility. | PR for affected contracts. |
| End-to-end smoke | Critical user journey. | Main branch, release candidate, or safe environment. |
| Performance concept | Throughput or latency trend. | Scheduled or release candidate. |

Tags, suites, and naming conventions help CI run the right checks at the right time.

## Parallel Execution And Sharding

Parallel test execution shortens feedback but can expose hidden state leakage. Test sharding splits tests across workers. Both require tests to be independent and deterministic.

Watch for:

- Tests that rely on execution order.
- Shared files or ports.
- Shared database rows.
- Global mutable state.
- Clock and random assumptions.

## Build Reproducibility

Reproducible builds depend on controlled inputs:

- Pinned dependency versions.
- Consistent Java versions.
- Clean checkout state.
- Clear cache invalidation rules.
- No hidden local files.
- No reliance on live production endpoints.

Dependency caching speeds up CI, but stale caches can hide dependency or generated-output problems. Cache keys should change when dependency descriptors change.

## Secret Handling In Tests

Tests should not require real credentials for ordinary validation. Use clearly labeled fake values, local-only configuration, or safe sandbox environments when needed. Never print secrets in logs or artifacts.

