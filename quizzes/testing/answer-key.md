# Testing And Quality Engineering Answer Key

## Multiple Choice

1. B
2. C
3. C
4. A
5. B
6. B

## Short Answer Highlights

1. Verification checks against the specification; validation checks whether the specification solves the real need.
2. Coverage does not prove assertions, requirements, data combinations, or integration behavior are correct.
3. State: stock decreased after reservation. Behavior: notifier recorded one sent message.
4. Injecting `Clock` removes real-time flakiness and makes edge instants repeatable.
5. Regression protects a known bug fix; smoke checks whether the main path is worth deeper testing.
6. Retries can mask timing, isolation, or environment defects.
7. Choose integration when real adapters, serialization, persistence, configuration, or boundary behavior matters.
8. A surviving mutant suggests a missing or weak assertion unless the mutant is equivalent or irrelevant.
9. Required checks block unsafe changes; advisory checks preserve useful signals without creating noisy blockers.
10. Include known risks, defect triage, observability, rollback or mitigation evidence, and manual findings.

## Classification And Analysis

- Test doubles: 1 fake, 2 stub, 3 spy, 4 dummy, 5 mock.
- Smells: 1 state leakage or order dependency, 2 implementation coupling, 3 arbitrary sleep, 4 obscure fixture, 5 false negative or assertion-free test.
- Test levels: 1 unit, 2 component, 3 integration, 4 contract, 5 end-to-end.
- Coverage/mutation: low-risk uncovered branches may be documented or tested later; a surviving pricing boundary mutant likely needs equality boundary tests; rising defects despite coverage asks whether tests assert meaningful behavior.
- Flaky diagnostics: inspect data cleanup, record random seed, isolate temp directories, capture timing and worker metadata.
- CI gates: compilation and fast unit tests are usually required; full end-to-end and coverage trend are often advisory; severe new static-analysis warnings may be required depending on rule quality.

## Java Code Reading

1. `Clock` and `IdGenerator`.
2. Return the existing reservation for the same request ID.
3. Assert zero, negative, and over-limit quantities are rejected with clear errors.
4. Save once, repeat the same request, and assert the repository state remains consistent.
5. Implementation-coupled or over-specified test.

## Release Confidence

The failed contract test is the strongest blocker because it indicates a consumer compatibility break. The quarantined smoke test, coverage drop, and confusing error text need explicit risk review. Useful follow-up evidence includes contract fix or migration plan, flaky-test diagnosis, targeted tests for newly uncovered risk, and exploratory retest of the error flow. Passing tests alone is insufficient because one configured compatibility check failed and other quality signals changed.

