# Lease and Heartbeat Model

Workers register with capacity and partition ownership. Leases include a worker id and expiration time. Expired leases return tasks to ready state with incremented attempts so another worker can safely retry.
