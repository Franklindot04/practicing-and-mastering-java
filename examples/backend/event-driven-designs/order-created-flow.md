# Order Created Flow

## Scenario

A customer places an order. The order boundary saves the order and records that `OrderCreated` happened.

## Participants

- Customer client.
- Order API.
- Order data store.
- Payment planning consumer.
- Notification consumer.
- Reporting consumer.

## Flow

```text
Customer client
  |
  v
Order API
  |
  +--> Save order
  |
  v
OrderCreated
  |
  +--> Payment planning consumer
  |
  +--> Notification consumer
  |
  +--> Reporting consumer
```

## Failure Considerations

- If saving the order fails, `OrderCreated` should not be emitted.
- If notification fails, the order may still exist and need retry or investigation.
- If reporting is delayed, the customer should not necessarily wait.
- Consumers should tolerate duplicate `OrderCreated` events.

## Review Questions

- Which participant owns the meaning of `OrderCreated`?
- Which consumer reactions are required before the user receives a response?
- What event fields would consumers need?
- How would operators notice that notifications stopped processing?
