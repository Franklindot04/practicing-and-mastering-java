# Backend System Design Architecture Scenario Quiz

## SC1

A startup with one backend team wants separate services for catalogue, pricing, inventory, checkout, payment, orders, shipment, and notification before launch. The team also needs strict inventory correctness and has no on-call process yet. Recommend an initial architecture and explain the trade-offs.

## SC2

Product search must return results in under 200 ms, but catalogue administration updates can appear in search up to 60 seconds later. Design the source-of-truth and derived-view architecture.

## SC3

Checkout sometimes times out after sending a payment authorization request. Design idempotency and user-visible states for retrying safely.

## SC4

Leadership asks for active-active multi-region checkout. The current database replicates asynchronously and lag can reach five minutes. RPO is one minute. Decide whether failover is safe and explain.

## SC5

A support admin endpoint can refund payments. Describe trust boundaries, authorization, audit, rate limiting, and observability requirements.
