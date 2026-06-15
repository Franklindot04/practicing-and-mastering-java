# Cloud Architecture Overview

Cloud architecture describes how a system should run in a cloud environment. It includes compute, networking, databases, storage, security boundaries, observability, scaling, failure handling, deployment flow, and cost awareness.

## What Cloud Architecture Means

For a Java backend, cloud architecture asks questions such as:

- How does traffic reach the service?
- How many backend instances can run?
- Where does state live?
- What happens when one instance fails?
- Which data belongs in a database, cache, queue, or object store?
- How are cost and reliability balanced?

## Tradeoffs

Architecture is a set of tradeoffs. More redundancy can improve availability but increase cost. More caching can improve read performance but introduce stale data. More async processing can smooth traffic spikes but make debugging harder.

Good architecture names the tradeoff instead of hiding it.

## Vendor-Neutral Learning

The examples in this repo use generic terms such as load balancer, managed database, object storage, queue, cache, and backend service. Real providers use specific products, limits, identity systems, and pricing models. Learn the concept here before choosing provider-specific designs.

