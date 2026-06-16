# Traffic Splitting And Canary

Traffic splitting sends different percentages of requests to different versions of a service. A canary release uses that idea to send a small amount of traffic to a new version before sending all traffic there.

## Example Scenario

The `task-api` calls a `notification-service`. Version 1 is already serving users. Version 2 changes message formatting. A canary might start by sending a small percentage of internal calls to version 2 while metrics and errors are watched.

## What To Watch

- Request success rate.
- Latency percentiles.
- Error types.
- Retry volume.
- Saturation or resource pressure.
- Business signals that show incorrect behavior.

## Why Mesh Policy Helps

A mesh can shift traffic without changing application code. That can be useful when many callers need the same routing rule.

## What The Mesh Cannot Know

The mesh can see traffic signals, but it does not know whether a task notification is semantically correct. Application tests, logs, and business checks still matter.

## Beginner Rule

Traffic splitting should be gradual, observable, reversible, and tied to clear rollback criteria.

