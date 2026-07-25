# Inventory Update Flow

## Scenario

Inventory changes after a stock adjustment. Other parts of the system react to `InventoryAdjusted`.

## Participants

- Inventory admin client.
- Inventory API.
- Inventory data store.
- Reorder planning consumer.
- Product availability consumer.
- Reporting consumer.

## Flow

```text
Inventory admin client
  |
  v
Inventory API
  |
  +--> Save stock adjustment
  |
  v
InventoryAdjusted
  |
  +--> Reorder planning consumer
  |
  +--> Product availability consumer
  |
  +--> Reporting consumer
```

## Failure Considerations

- Consumers may need the adjustment amount and resulting quantity.
- Product availability may be temporarily stale if processing is delayed.
- Reorder planning should avoid creating duplicate reorder suggestions.
- Reporting can usually lag behind operational stock changes.

## Review Questions

- Should this be an event notification or event-carried state transfer?
- Which fields identify the adjusted item?
- What happens if two adjustments occur close together?
- Which consumer has the strictest freshness requirement?
