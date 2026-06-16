# Tracing, Metrics, And Access Logs

Traces, metrics, and access logs answer different observability questions.

## Traces

Traces show the path of a request across services. They are useful when a request passes through several dependencies and one hop is slow or failing.

Ask:

- Which services handled the request?
- Which span was slow?
- Were retries visible?
- Did the trace context propagate correctly?

## Metrics

Metrics show aggregated behavior over time. They are useful for alerting, dashboards, and trend analysis.

Ask:

- Is error rate rising?
- Are latency percentiles worse after a release?
- Did retry volume increase?
- Are sidecar resources saturated?

## Access Logs

Access logs record individual request details at the proxy boundary. They can help debug routing and policy issues, but they must avoid sensitive data.

## Balanced Use

Metrics tell you something changed. Traces help you follow one request. Logs provide detail. A mature system usually needs all three, with retention and privacy controls.

