# Consistency Model

Checkout uses immediate consistency for inventory reservation and payment idempotency inside one process. Search projection and notification are eventually consistent because they depend on outbox processing. Cache-aside reads may be stale until eviction.
