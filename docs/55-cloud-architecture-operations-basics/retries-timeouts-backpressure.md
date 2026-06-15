# Retries, Timeouts, And Backpressure

## Timeouts

Timeouts prevent callers from waiting forever. Every network dependency should have a timeout that matches user expectations and upstream capacity.

## Retries

Retries can hide brief failures, but aggressive retries can amplify outages. Retries need limits, delays, and awareness of whether an operation is safe to repeat.

## Backpressure

Backpressure is a way to slow or reject work when the system is saturated. It protects dependencies from collapse.

Examples:

- Reject requests with a clear error when overloaded.
- Limit queue producers.
- Reduce worker concurrency.

## Circuit Breaker Concept

A circuit breaker stops calling a failing dependency for a period of time. It can reduce repeated failures, but it needs monitoring and careful fallback behavior.

