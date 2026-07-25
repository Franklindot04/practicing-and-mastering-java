# Idempotent Consumers And Ordering

An idempotent consumer can safely receive the same logical message more than once without applying the business effect more than once.

## Idempotent Consumer Design

A Java consumer can use:

- Message identifiers
- Domain operation identifiers
- Unique database constraints
- Processed-message tables
- State checks before side effects

## Example

If a consumer receives `InvoicePaid` twice, it should not send two receipts or add money twice.

```text
Receive InvoicePaid(invoiceId=100)
  |
  +--> already processed invoice payment?
        |
        +--> yes: acknowledge and skip
        |
        +--> no: apply effect, record processed id, acknowledge
```

## Message Ordering

Ordering means messages are processed in a predictable sequence.

Ordering is limited by:

- Broker model
- Queue concurrency
- Partitioning strategy
- Consumer group behavior
- Retries and redelivery
- Slow or failed consumers

## Ordering Limitations

Strict ordering usually reduces parallelism.

If `TaskCreated`, `TaskAssigned`, and `TaskClosed` must be processed in order, the system needs a stable key such as `taskId` and a consumer strategy that respects that key.

## Review Questions

1. Why do idempotent consumers matter for at-least-once delivery?
2. How can retries affect ordering?
3. Why can strict ordering reduce throughput?
