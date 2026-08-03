# Idempotency Design

Workflow submission idempotency maps a caller key to a stable workflow id. Completion idempotency stores side-effect keys so repeated completion messages do not create extra effects.

## Purpose

Idempotency makes retries safer when clients, workers, or networks are uncertain. It does not guarantee exactly-once execution; it provides stable handling for repeated requests with the same identity.

## Failure Behaviour

Duplicate workflow submission returns the original workflow. Duplicate completion returns a duplicate result rather than recording another side effect. Completion without a valid lease is rejected because idempotency cannot replace ownership checks.

## Production Comparison

A real system would store idempotency records durably with expiry, ownership, result payload, and conflict handling. It would also protect against accidental key reuse across tenants or operations.
