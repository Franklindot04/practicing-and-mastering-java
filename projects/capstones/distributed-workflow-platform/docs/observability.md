# Observability

Operational reports expose workflow count, task count, active leases, retries, dead letters, duplicates, rejections, and quarantined tasks. State records provide an audit trail for replay reasoning.

## Purpose

Observability is included so workflow behaviour can be reviewed from the outside: whether work is admitted, leased, completed, retried, rejected, duplicated, or quarantined.

## Signals

- Workflow and task counts show backlog shape.
- Active leases show work currently assigned.
- Retry counts show recovery pressure.
- Dead-letter and quarantine counts show unsafe work.
- Duplicate counts show idempotency activity.
- Rejection counts show admission control pressure.
- State records explain replay order.

## Monitoring Expectations

A deployed platform would track queue depth, task age, lease expiration rate, heartbeat lag, worker capacity, hot partitions, retry exhaustion, dead-letter age, duplicate completion rate, and replay outcomes. Alerts should be tied to stuck workflows, customer-visible delay, or growing unsafe backlog.

## Limitations

The capstone does not export metrics, traces, logs, or dashboards. It provides local reports that tests can assert. That is enough for learning review, but not enough for operating a real workflow platform.
