# Advanced Testing Techniques

Advanced testing techniques are useful when example-based tests leave too much uncertainty. They should be introduced because the risk justifies them, not because they look sophisticated.

## Property-Based And Generative Testing

Property-based tests generate many inputs and check invariants. Instead of testing only `sort([3, 1, 2])`, a property can verify that sorting preserves size, preserves elements, and returns ordered output for many generated lists. Use reproducible seeds so failures can be replayed. Shrinking reduces a failing case to a smaller example; when a library is not used, document the simplified reproduction path.

Metamorphic testing checks relationships between executions. If adding a neutral element should not change a result, or converting units twice should preserve value within tolerance, those relationships can find defects without needing an oracle for every exact output.

## Mutation Testing

Mutation testing changes code in small ways and checks whether tests fail. A survived mutation suggests tests may not assert meaningful behavior. Mutation score is useful feedback, but it is not proof of quality. Equivalent mutations, generated code, and low-risk code can distort the number.

## Fuzz, Combinatorial, And Model-Based Testing

Fuzz testing searches for parser, validation, and robustness defects using unusual inputs. Combinatorial testing chooses representative combinations when exhaustive testing is impossible. Model-based testing compares implementation behavior to a simpler model. State-machine testing is valuable for workflows such as reservation, payment, retry, and cancellation.

## Concurrency Testing

Concurrency tests should avoid hoping that races appear. Prefer deterministic schedulers, barriers, latches, small bounded workloads, and clear invariants. Test safety properties such as no negative inventory, no duplicate completion, and no lost updates. Liveness properties need timeouts, but the timeout should be short, bounded, and diagnostic.

## Fault Injection And Replay

Fault injection deliberately triggers failures such as timeout, partial response, duplicate message, stale cache, rejected write, or exhausted pool. Chaos testing expands this idea in running systems, but it belongs behind safety boundaries and clear rollback rules. Replay-based testing uses captured inputs or events to reproduce defects; sanitize sensitive data and keep fixtures small.

## Snapshot, Golden File, And Architecture Testing

Snapshot and golden-file tests work best for stable textual or serialized outputs. They become noisy when output changes often or reviewers cannot understand the diff. Architecture tests check rules such as domain code must not depend on framework controllers, persistence adapters must implement ports, or public APIs must not expose entities. Architecture checks can run in default tests when they are fast and deterministic.
