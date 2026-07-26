# Data Requirements And Ownership

Start data design by naming the data the system owns and the decisions that depend on it.

## Data Requirements

Ask:

- What data does the system create?
- What data does the system only reference?
- Which fields are required?
- Which fields are optional?
- Which data changes often?
- Which data is append-only?
- Which data is sensitive?
- Which data must be auditable?
- How long must data be retained?

Example for an order system:

| Data | Owner | Changes? | Sensitivity | Notes |
| --- | --- | --- | --- | --- |
| Order | Order domain | Yes | Medium | Status changes over time |
| Payment authorization | Payment boundary | Yes | High | Do not store card secrets |
| Inventory reservation | Inventory domain | Yes | Medium | May expire |
| Notification preference | User profile domain | Yes | Medium | Referenced by notification flow |

## Data Ownership

Ownership means one part of the system is responsible for deciding the truth of that data.

```text
Order Service owns order state
Payment Boundary owns payment result interpretation
Inventory Boundary owns stock reservation state
```

When multiple services write the same data independently, correctness becomes difficult.

## Relational Versus Non-Relational Choices

| Need | Relational May Fit | Non-Relational May Fit |
| --- | --- | --- |
| Strong relationships | Foreign keys and joins help | Data duplication may be awkward |
| Flexible document shape | Schema changes may be heavier | Document model may be natural |
| Known transactional writes | ACID transactions help | Some stores have narrower transaction scope |
| High-volume key lookups | Works with good indexes | Key-value or document stores may be simpler |
| Complex ad hoc queries | SQL is strong | Query model may be limited |

The question is not which category is modern. The question is which data model supports the access patterns and correctness needs.

## Privacy Boundaries

Privacy boundaries define where sensitive data is allowed to flow.

Ask:

- Does this component need the raw value?
- Can it use a token, reference, or summary?
- Should this field appear in logs?
- Should this field appear in events?
- Who can query this data?
- When should this data be deleted or anonymized?

Simplified examples should never use real secrets, real credentials, or production identifiers.
