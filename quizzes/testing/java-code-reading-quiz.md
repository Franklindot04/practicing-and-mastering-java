# Java Code Reading Quiz

Read the code and answer the questions.

```java
final class ReservationService {
    private final Clock clock;
    private final IdGenerator ids;
    private final InventoryRepository repository;

    Reservation reserve(String requestId, String sku, int quantity) {
        repository.findByRequestId(requestId)
                .ifPresent(existing -> {
                    throw new DuplicateRequestException(existing.id());
                });
        Reservation reservation = new Reservation(ids.nextId(), requestId, sku, quantity, Instant.now(clock));
        repository.save(reservation);
        return reservation;
    }
}
```

1. Which dependencies make time and IDs deterministic in tests?
2. What idempotency behavior might be better than throwing on duplicate requests?
3. What unit test would you write for invalid quantity if validation existed here?
4. What component test would you write around the repository boundary?
5. What test smell would appear if a test verified each private step instead of the result?

## Release-Confidence Scenario

A release candidate has:

- Unit and component tests passing.
- One flaky end-to-end smoke test quarantined.
- Coverage dropped from 82% to 77%.
- A contract test failed for one consumer.
- Manual exploratory testing found confusing error text.

Questions:

1. Which finding is most likely to block release?
2. Which findings need risk acceptance?
3. What follow-up evidence would improve confidence?
4. Why is "tests passed" not enough here?

