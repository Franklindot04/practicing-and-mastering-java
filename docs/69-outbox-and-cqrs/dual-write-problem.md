# Dual-Write Problem

The dual-write problem is a specific form of the transactional messaging problem.

It happens when one operation writes to two different places that do not share one reliable transaction.

## Example

```text
Task API
  |
  +--> Write task row
  |
  +--> Write event to external destination
```

If the first write succeeds and the second write fails, the system is inconsistent.

## Common Symptoms

- Database state exists but no event was published.
- An event was published but the related state was rolled back.
- Retry logic publishes duplicates.
- Operators cannot tell which side is correct.

## Why Simple Retry Is Not Enough

A retry may not know whether the previous attempt failed before or after the second write. Retrying blindly can create duplicates or publish events for invalid state.

## Learning Rule

If one business action writes to two independent places, design for partial success.
