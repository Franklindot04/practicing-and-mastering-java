# Data Stores Ownership And Schema Design

Data-store selection should start with questions: what must be queried, what must be updated atomically, how much data is retained, who owns it, what failure modes are acceptable, and how operators will backup, restore, migrate, and delete it.

## Store Types

Relational databases fit transactional workloads, constraints, joins, indexes, and mature operational practices. They are often a strong default for core business records such as orders, payments, inventory reservations, accounts, and audit metadata.

Document databases fit aggregate-oriented records where the application commonly reads and writes a whole document and schema variation is expected. They still need indexes, validation, migrations, backup, and consistency analysis.

Key-value stores fit simple high-throughput access by key, sessions, rate-limit counters, feature flags, and cache-like data. They are weak when arbitrary queries or multi-record transactions are central.

Wide-column stores fit high write volume, large scale, and query patterns designed around partition keys. They demand careful modelling because query flexibility is limited.

Graph databases fit relationship-heavy queries such as fraud networks, recommendations, dependencies, and permissions graphs. They are not a universal replacement for relational modelling.

Search engines fit full-text search, ranking, faceting, autocomplete, and relevance tuning. They are usually derived stores, not the source of truth for business invariants.

Time-series databases fit metrics, events over time, retention windows, downsampling, and time-based queries.

Object storage fits large immutable or versioned blobs such as images, exports, logs, and backups. Metadata ownership and lifecycle policy still matter.

Avoid simplistic SQL-versus-NoSQL rules. A system may use relational storage for orders, search for catalogue discovery, object storage for images, and a queue-backed projection for recommendations.

## Ownership

Each dataset needs an owner:

- source of truth
- write authority
- read contract
- migration owner
- retention and deletion owner
- backup and restore owner
- data classification
- audit and access rules

Shared databases across services are a coupling risk. If unavoidable during migration, define which service writes each table, which columns are stable, and how compatibility will be maintained.

## Schema And Model Design

Normalization reduces duplication and update anomalies. Denormalization improves read performance and isolation when data is duplicated intentionally. Both choices require a consistency plan.

Indexes speed reads but slow writes and consume storage. Indexes should match query shape, filtering, sorting, pagination, and cardinality. Unbounded secondary indexes in partitioned systems can create hidden cross-partition work.

Read and write models may differ. A normalized write model can protect invariants while projections or materialized views serve fast reads.

Schema evolution should prefer backward-compatible expansion, dual writes only with caution, backfills with stop conditions, and expand-contract migrations. Removing or renaming fields requires a compatibility window.

## Lifecycle

Data architecture must include backup, restore, retention, archival, deletion, legal hold, and data sovereignty. A backup that has never been restored is an untested assumption. A delete request that leaves derived views, caches, search indexes, logs, or exports untouched is incomplete.
