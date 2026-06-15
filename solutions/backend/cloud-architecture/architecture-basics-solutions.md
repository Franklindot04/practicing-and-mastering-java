# Cloud Architecture Basics Solutions

## Exercise 1

Application architecture includes controllers, services, repositories, validation, DTOs, and business rules. Infrastructure architecture includes runtime instances, load balancing, database, network paths, secrets delivery, logs, metrics, and scaling.

A decision that affects both is storing session state in memory, because it changes application behavior and load-balancing options.

## Exercise 2

The backend service can be a single point of failure if only one instance runs. Mitigation: run multiple healthy instances behind a load balancer. The database can also be a single point of failure. Mitigation: use managed backup/failover features and test recovery.

Mitigation can increase cost because redundancy, backups, monitoring, and managed features consume resources.

