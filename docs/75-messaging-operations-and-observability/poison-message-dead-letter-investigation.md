# Poison Message And Dead Letter Investigation

Dead letters need ownership. A dead-letter destination is useful only if people inspect and resolve it.

## Poison Message Signs

- Same message fails repeatedly.
- Failures happen immediately.
- Failure reason is validation or schema related.
- Retry attempts do not improve the outcome.

## Dead-Letter Investigation

Collect:

- Message id
- Correlation id
- Message type
- Payload version
- Failure reason
- Attempt count
- Consumer version
- Time first failed
- Time dead-lettered

## Recovery Options

- Fix consumer code and replay safely.
- Correct bad data with an auditable process.
- Add compatibility support for an older payload.
- Discard only when the business owner accepts the loss.

## Common Mistakes

- Retrying poison messages forever.
- Replaying without fixing the cause.
- Deleting dead letters without a record.
- Treating every dead letter as the same severity.
