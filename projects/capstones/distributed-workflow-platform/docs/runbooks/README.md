# Runbooks

- Lease expiration: inspect task id, worker id, attempt count, and expiration time before reassignment.
- Poison task: quarantine, inspect payload and handler behavior, then decide whether to repair, replay, or discard.
- Hot partition: identify partition owner, pending task count, and whether routing or sharding should change.
- Admission rejection: confirm capacity pressure and prefer bounded rejection over unbounded queue growth.
