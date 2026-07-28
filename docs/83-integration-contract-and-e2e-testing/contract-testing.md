# Contract Testing

Contract tests protect agreements between consumers and providers. They are especially useful when teams release independently or when a full end-to-end environment is expensive.

## Consumer And Provider Contracts

Consumer-driven contract testing starts with what the consumer needs. Provider contract testing checks that the provider still satisfies published behavior.

Example contract concerns:

- Required fields remain available.
- New optional fields do not break older consumers.
- Status values are documented and compatible.
- Error responses keep stable shapes.
- Message schemas evolve without removing required data.

## Schema Compatibility

| Change | Usually compatible? | Notes |
| --- | --- | --- |
| Add optional response field | Yes | Consumers should ignore unknown fields. |
| Remove required response field | No | Existing consumers may fail. |
| Rename field | No | Treat as remove plus add unless versioned. |
| Add required request field | No | Older consumers cannot send it. |
| Add enum value | Maybe | Consumers need a safe unknown-value path. |

## API Compatibility Example

```json
{
  "reservationId": "res-123",
  "sku": "book-1",
  "quantity": 2,
  "status": "RESERVED"
}
```

A consumer may only care that `reservationId` and `status` exist. A provider can add `reservedAt` safely if consumers ignore unknown fields. Removing `status` is a breaking change.

## Contract Ownership Checklist

- Who owns the contract?
- Where is it reviewed?
- How are versions handled?
- Which compatibility rules are required?
- What happens when provider and consumer expectations disagree?
- Which failures block release, and which start a migration discussion?

Contract testing is not a replacement for integration or end-to-end tests. It narrows a specific compatibility risk.

