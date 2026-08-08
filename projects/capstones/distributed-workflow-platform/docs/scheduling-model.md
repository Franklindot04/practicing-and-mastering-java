# Scheduling Model

Scheduling is deterministic: the platform selects the lowest task id that is ready, whose dependencies are complete, and whose partition matches the worker. This makes tests repeatable and keeps scheduling behavior explainable.

## Purpose

The scheduler demonstrates task selection rules without hiding them behind a real queue. It answers three questions: is the task ready, are dependencies complete, and is there a worker responsible for the task partition?

## Assumptions And Constraints

- Scheduling runs in one JVM.
- Worker capacity is modeled but not connected to real CPU or thread-pool utilization.
- Partition ownership is explicit and deterministic.
- Tests must not depend on wall-clock sleeps or background polling.

## Trade-Offs

Choosing the lowest ready task id makes assertions stable. A production scheduler might use priority, fairness, deadlines, queue age, tenant limits, or cost-aware placement. Those policies are valuable, but they can make basic recovery scenarios harder to reason about. This capstone keeps scheduling boring so lease, retry, dependency, and replay behaviour stay visible.

## Failure Behaviour

If dependencies are incomplete, a task remains unavailable for lease. If no worker owns the partition, no lease is issued. If capacity pressure rejects a workflow, no tasks are created for it. These behaviours are explicit rejections or absences rather than hidden background failures.

## Future Improvements

Useful extensions would add priority queues, workflow deadlines, per-tenant fairness, worker capability matching, and starvation detection. Each would need tests showing why the added complexity is worth it.
