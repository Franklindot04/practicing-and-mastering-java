# Test Strategy Checklist

Use this checklist when planning or reviewing a Java feature.

## Feature Boundary

- What behavior is the system under test responsible for?
- Which collaborators are inside the test boundary?
- Which dependencies should be real, fake, stubbed, or mocked?
- What state must be isolated between tests?
- What time, randomness, file-system, or environment assumptions exist?

## Risk Review

- What would hurt users most if it failed?
- What has changed recently?
- Which paths are complex, asynchronous, or concurrent?
- Which external contracts can break consumers?
- Which behavior is hard to observe after release?

## Test Mix

| Need | Candidate test |
| --- | --- |
| Fast feedback for pure logic | Unit test |
| Confidence in service orchestration | Component test |
| Adapter or persistence behavior | Integration test |
| API compatibility | Contract test |
| Main workflow confidence | End-to-end smoke test |
| Unknown usability or workflow risk | Exploratory testing |

## Release Confidence Questions

1. What evidence says this change works?
2. What evidence says it fails safely?
3. What important risk is not covered by automation?
4. Which tests are slow, flaky, or expensive to maintain?
5. Which failures would be hard to diagnose?
6. What should block release, and what should be advisory?

## Common Review Comments

- "This unit test duplicates the implementation but does not state the expected behavior."
- "This end-to-end test covers a rule that could be tested faster at component level."
- "The test uses real time; inject a `Clock` so it is deterministic."
- "The suite has coverage, but no negative path for invalid input."
- "The test waits five seconds; use bounded polling for the condition instead."
- "This behavior crosses an API boundary; consider a contract test."

