# Flaky Tests, Coverage, And Quality Gates

A flaky test sometimes passes and sometimes fails for the same code. Flakiness destroys trust because engineers stop treating failures as useful evidence.

## Common Causes

Flaky tests often come from timing dependencies, race conditions, shared state, environmental dependence, test ordering, unseeded randomness, filesystem leftovers, ports already in use, time zones, locale differences, external APIs, overloaded build agents, and assertions against eventually consistent behavior without a deterministic wait condition.

Retries can reduce noise, but they can also hide real defects. Quarantine may be necessary to unblock delivery, but quarantine must create ownership, diagnosis, and a repair deadline.

## Repair Strategy

Repair starts by making the failure observable. Capture seed values, inputs, thread names, correlation IDs, relevant state, and concise logs. Replace sleeps with latches, barriers, injected clocks, deterministic schedulers, or explicit bounded polling. Remove ordering assumptions. Isolate global state. Make test data unique. Separate slow tests from fast gates when necessary.

Track stability metrics such as failure rate, rerun rate, duration variance, and time to repair. A suite that is stable and diagnostic creates better confidence than a larger suite that developers distrust.

## Coverage

Line coverage reports executed lines. Branch coverage reports decision paths. Condition coverage reports boolean subexpressions. These metrics can expose untested areas, but high coverage does not prove quality. A suite can execute a line without asserting the behavior that matters.

Mutation score can reveal weak assertions, but it also needs human interpretation. Meaningful assertions, good test data, boundary cases, and integration evidence matter more than vanity numbers.

## Quality Gates

A quality gate is a decision rule. Useful gates include compiler warnings, formatting, static analysis, deterministic unit tests, architecture rules, dependency checks, vulnerability scanning, new-code coverage thresholds, mutation analysis for selected risk areas, and release smoke tests.

New-code gates are often more practical than historical whole-repository gates because they improve the codebase without freezing progress. Main-branch gates should be fast and reliable. Release gates can be deeper, including integration suites, migrations, compatibility checks, and operational readiness.

A good gate explains what failed, why it matters, who owns it, and how to reproduce it. A bad gate produces a number with no decision value.

## Quality Engineering Mindset

Quality engineering is not only writing tests. It includes design for testability, clear contracts, observability, safe defaults, failure-mode thinking, code review, automation, release discipline, incident learning, and removing sources of repeated defects. More tests do not automatically mean more confidence; better evidence does.
