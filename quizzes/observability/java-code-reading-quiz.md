# Java Code Reading Quiz

Review this fictional Java snippet:

```java
logger.error("failed email=" + email + " authorization=" + authHeader, ex);
metrics.increment("checkout.failure." + requestId);
executor.submit(() -> paymentClient.charge(request));
```

1. Identify the logging safety issue.
2. Identify the metric cardinality issue.
3. Identify the context propagation issue.
4. Rewrite the metric idea with safe dimensions.
5. What test would show context does not leak between executor tasks?

Review this health-check sketch:

```java
boolean healthy = database.insertProbeRow();
return healthy ? "UP" : "DOWN";
```

6. What side effect makes this risky?
7. What timeout concern is missing?
8. How might readiness differ from liveness here?

