# Backend System Design Failure Analysis Quiz

## FA1

During a sale, one product's partition becomes overloaded while other partitions are idle. Identify the likely design issue and mitigation options.

## FA2

A consumer repeatedly fails on one malformed event and retry volume keeps rising. Diagnose the failure and propose safe dead-letter handling.

## FA3

Search results show deleted products after catalogue deletion. Identify possible causes across source data, cache, projection, and index lifecycle.

## FA4

Checkout availability drops when notification latency rises. Identify the architecture smell and redesign the dependency.

## FA5

A new event version removes `customerId`, and old consumers fail. Explain the compatibility mistake and safer rollout.
