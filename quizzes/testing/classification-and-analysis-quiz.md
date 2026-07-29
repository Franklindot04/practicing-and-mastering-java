# Classification And Analysis Quiz

## Test-Double Classification

Classify each as dummy, stub, spy, mock, or fake.

1. An in-memory repository that stores orders in a map.
2. A tax service replacement that always returns `0.07`.
3. A notifier that records every message sent for later assertions.
4. A collaborator passed to satisfy a constructor but never used.
5. A payment gateway expectation that must be called once with a specific request.

## Test-Smell Identification

Name the likely smell.

1. A test fails only when the full suite runs.
2. A test verifies seven private helper calls in exact order.
3. A test sleeps for ten seconds before checking an async result.
4. A test has a large shared fixture where the important value is hard to find.
5. A test passes even when the assertion is removed.

## Test-Level Selection

Choose unit, component, integration, contract, or end-to-end.

1. Verify `quantity` rejects `0` and `100`.
2. Verify an order service reserves stock using an in-memory repository.
3. Verify a repository maps rows to domain objects correctly.
4. Verify a provider still returns fields required by a consumer.
5. Verify a user can complete the primary checkout journey.

## Coverage And Mutation Interpretation

1. A branch is uncovered but low risk. What might you do?
2. A mutant changes `<=` to `<` and survives in pricing code. What might be missing?
3. Branch coverage increased but defect escapes rose. What question should the team ask?

## Flaky-Test Analysis

For each cause, suggest one diagnostic.

1. Shared database state.
2. Random generated input.
3. Parallel file writes.
4. CI worker performance variance.

## CI Quality-Gate Quiz

Classify as usually required or usually advisory for a small Java learning project.

1. Compilation.
2. Fast unit tests.
3. Full end-to-end suite.
4. Coverage trend.
5. Severe new static-analysis warning.

