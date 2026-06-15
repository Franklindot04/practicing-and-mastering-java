# Managed Databases And Object Storage

## Managed Databases

A managed database is operated by a platform or provider. The provider may handle backups, patching, replication, monitoring hooks, and failover options.

Managed does not mean responsibility-free. Teams still choose schema design, indexes, connection limits, backup retention, access control, and cost settings.

## Connection Pooling

Java backends usually use a connection pool so requests can reuse database connections. Too many backend instances with too many connections can overload the database.

Cloud architecture review should connect scaling decisions with database connection limits.

## Object Storage

Object storage stores files such as images, exports, logs, reports, or backups as objects. It is different from a relational database.

Use object storage for file-like data and store metadata in the database when needed.

## CDN Concept

A CDN can cache and serve static content closer to users. It can improve latency and reduce origin load, but it adds cache behavior and invalidation decisions.

