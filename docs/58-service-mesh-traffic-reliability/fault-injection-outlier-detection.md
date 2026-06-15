# Fault Injection And Outlier Detection

Fault injection deliberately adds failures, delays, or errors to test how a system behaves. Outlier detection identifies unhealthy service instances and reduces or removes traffic to them.

## Fault Injection

Fault injection can help answer questions such as:

- What happens when a dependency is slow?
- Does the caller time out safely?
- Are errors logged clearly?
- Does retry behavior overload the dependency?
- Does the user receive a useful response?

Fault injection belongs in controlled learning, test, or staging environments. It should not be casually enabled for real users.

## Outlier Detection

Outlier detection watches backend instances for repeated failures or abnormal behavior. A mesh may temporarily avoid an instance that appears unhealthy.

## Limits

Outlier detection is not a substitute for health checks, readiness probes, capacity planning, or fixing application bugs. It is one protection layer.

## Learning Exercise

Given three service instances, imagine one starts returning errors. Trace what the caller sees with no outlier detection, then with outlier detection, then with retries and timeouts layered on top.

