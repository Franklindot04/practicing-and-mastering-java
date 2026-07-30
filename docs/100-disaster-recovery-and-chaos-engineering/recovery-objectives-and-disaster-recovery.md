# Recovery Objectives And Disaster Recovery

Recovery objectives define acceptable recovery time and data loss. Backups, replication, and failover are only useful when restore and validation have been practiced.

## Coverage Notes

### RTO

Recovery time objective is the target maximum time to restore service after disruption.

Java angle: A Java service may need startup, dependency, and data checks in its RTO.

Tradeoff or failure case: RTO is not guaranteed by restart alone.

### RPO

Recovery point objective is the target maximum acceptable data loss window.

Java angle: Durable writes and replication influence RPO.

Tradeoff or failure case: A backup older than the RPO is not enough.

### Maximum Tolerable Downtime

Maximum tolerable downtime is the business limit beyond which harm becomes unacceptable.

Java angle: It can be longer or shorter than the engineering RTO target.

Tradeoff or failure case: Do not confuse target with proof.

### Backup And Restore

Backup copies data; restore proves data can be recovered.

Java angle: Practice restore into a safe environment.

Tradeoff or failure case: Untested backups do not guarantee recoverability.

### Replication

Replication keeps copies updated across storage or regions.

Java angle: It may improve recovery but can replicate corruption.

Tradeoff or failure case: Replication is not a substitute for backups.

### Failover And Failback

Failover moves traffic to a recovery target; failback returns to normal.

Java angle: Plan dependency order and data direction.

Tradeoff or failure case: Failback is often riskier than failover.

### Active-Passive And Active-Active

Active-passive keeps standby capacity; active-active serves from multiple places.

Java angle: Each model changes testing and consistency work.

Tradeoff or failure case: Active-active is complex and not automatically better.

### Regional Failure

Regional failure removes a whole location or dependency zone.

Java angle: Local examples can model this as dependency unavailability.

Tradeoff or failure case: Cross-region recovery needs data, network, and identity planning.

### Corruption

Corruption is incorrect data, not just missing service.

Java angle: Detection and clean backups matter.

Tradeoff or failure case: Highly available corrupted data is still corrupted.

### Backup Integrity

Backup integrity proves the copy is complete and usable.

Java angle: Checksums, restore tests, and sample queries help.

Tradeoff or failure case: A successful backup job log is not enough.

### Restore Testing

Restore testing rehearses recovery and measures real time.

Java angle: Record duration, issues, and validation evidence.

Tradeoff or failure case: A plan that has never been run is unproven.

### Dependency Recovery Order

Services often require databases, queues, secrets, identity, and networking in a sequence.

Java angle: Runbooks should list order explicitly.

Tradeoff or failure case: Starting apps before dependencies can create noisy failures.

### DR Plans

A disaster-recovery plan names scope, triggers, owners, steps, communications, and validation.

Java angle: Keep the plan small enough to use under stress.

Tradeoff or failure case: Long prose without decisions slows response.

### Communications

Communication plans define internal updates, customer messaging, and status cadence.

Java angle: Reliability is partly organizational.

Tradeoff or failure case: Silence during recovery increases confusion.

### Ownership

Recovery ownership names decision makers and operators.

Java angle: Include escalation when owners are unavailable.

Tradeoff or failure case: Ambiguous ownership delays recovery.

### Recovery Validation

Validation confirms service behavior and data correctness after recovery.

Java angle: Use smoke tests, data checks, and SLO evidence.

Tradeoff or failure case: Do not declare recovery on process uptime alone.
