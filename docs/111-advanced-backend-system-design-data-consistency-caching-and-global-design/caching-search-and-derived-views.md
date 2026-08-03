# Caching Search And Derived Views

Caches and derived views improve read performance and user experience, but they introduce freshness, invalidation, ownership, and recovery questions. Caching is not transparent to correctness.

## Cache Patterns

Cache-aside lets the application read from cache, load from the backend on miss, and populate the cache. It is common and explicit.

Read-through hides loading behind the cache client or provider. It can simplify application code but may hide dependency and timeout behaviour.

Write-through writes cache and backend together. It can improve read freshness but adds write latency and failure modes.

Write-behind writes cache first and flushes later. It can improve write latency but is dangerous for correctness-critical data unless durability and replay are strong.

Local caches are fast but create per-instance inconsistency. Distributed caches centralize state but add network hops and cluster operations. HTTP caching and CDN concepts fit public or semi-public content with explicit freshness headers.

## Cache Failure Modes

Cache keys must include tenant, authorization-relevant dimensions, version, locale, and query parameters when those affect results. Weak keys can leak data or return wrong values.

Expiry reduces stale lifetime but does not solve invalidation. Invalidation must handle update paths, deletes, derived views, and regional caches.

Stampedes occur when many callers miss the same hot key and all load the backend. Use single-flight loading, request coalescing, jittered expiry, early refresh, or stale-while-revalidate where correctness allows it.

Cache penetration happens when repeated misses for nonexistent keys hit the backend. Negative caching can help, with short expiry and careful privacy semantics.

Hot keys can overload one cache node or backend record. Detect with metrics and plan sharding, replication, request coalescing, or product changes.

## Observability

Track hit rate, miss rate, load latency, backend load, evictions, expired entries, invalidations, stale responses, negative-cache hits, stampede prevention, and cache errors. A high hit rate is not automatically good if stale or unauthorized data is served.

## Search And Projections

Search indexes use inverted indexes, analyzers, ranking, facets, and autocomplete concepts. They are excellent for discovery but often lag source-of-truth updates.

Derived views include projections, materialized views, denormalized read models, feeds, and timelines. They can be built through fan-out on write or fan-out on read.

Fan-out on write precomputes recipient views when an event occurs. It can make reads fast but writes expensive and backfills complex. Fan-out on read computes at request time. It can reduce write cost but increase read latency.

Index freshness should have an SLO and user-visible expectations. Backfills and reindexing need throttling, progress metrics, validation, and rollback or stop conditions.

Cursor pagination is usually safer than offset pagination for changing feeds and large indexes. Duplicate suppression may be needed when projections update while users paginate.

Derived state must be rebuildable. If the source event retention is shorter than the required rebuild window, the architecture needs snapshots, archival events, or another recovery source.
