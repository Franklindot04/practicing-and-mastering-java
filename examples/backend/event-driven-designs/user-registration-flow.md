# User Registration Flow

## Scenario

A user registers an account. The account boundary records `UserRegistered` after validation succeeds.

## Participants

- Registration client.
- Account API.
- Account data store.
- Welcome message consumer.
- Audit consumer.
- Profile setup consumer.

## Flow

```text
Registration client
  |
  v
Account API
  |
  +--> Validate request
  |
  +--> Save user
  |
  v
UserRegistered
  |
  +--> Welcome message consumer
  |
  +--> Audit consumer
  |
  +--> Profile setup consumer
```

## Failure Considerations

- Invalid registration data should stop the event from being created.
- Welcome message failure should be visible for retry.
- Audit failure may require stronger operational attention than welcome messaging.
- Profile setup should be idempotent if the event is received twice.

## Review Questions

- Is `UserRegistered` an event or a command?
- Which fields should be excluded for privacy or security?
- Should the welcome message block account creation?
- What correlation identifier would help debug the flow?
