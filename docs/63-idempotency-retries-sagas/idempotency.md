# Idempotency

An operation is idempotent when performing it more than once has the same intended effect as performing it once.

## Simple Examples

Idempotent:

- Set task `completed=true`.
- Delete task `123`.
- Replace a profile name with "Sam".

Not naturally idempotent:

- Add 10 dollars to a balance.
- Create a new order.
- Send an email.
- Charge a card.

## Why It Matters

Distributed systems often retry requests because the caller cannot tell whether the first attempt worked.

```text
Client sends create order
API creates order
Network response fails
Client retries create order
```

Without idempotency, the retry may create two orders.

## Idempotency Keys

An idempotency key is a client-provided unique value that lets the server recognize a repeated request.

```text
POST /orders
Idempotency-Key: request-123

First request: create order and store result.
Retry: return the stored result instead of creating another order.
```

## Design Questions

- Which operations can be repeated safely?
- Which operations need an idempotency key?
- How long should completed request records be kept?
- What happens if the same key is reused with different input?
- What should logs show when duplicates are detected?

