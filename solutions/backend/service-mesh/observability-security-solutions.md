# Observability Security Solutions

## Exercise 1: Trace A Slow Request

Check metrics for latency and error trends, traces for the slow span, access logs for routing and response details, application logs for domain errors, dependency metrics for saturation, and deployment version labels for recent changes.

The goal is to connect the user-facing request with the internal dependency path instead of guessing from one signal.

## Exercise 2: Separate Identities

Workload identity proves which service is calling another service. User identity proves which person or client is making the business request. mTLS can protect `task-api` to `notification-service` communication, but application code still checks whether the user may complete the task.

mTLS does not solve broken input validation, overly broad domain authorization, or unsafe data handling.

