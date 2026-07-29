# Allocation, Reachability, And GC

Garbage collection removes objects that are no longer reachable. It does not remove objects that are accidentally still referenced, which is why managed languages can still have memory leaks.

## Allocation Concepts

| Concept | Meaning |
| --- | --- |
| Object allocation | Creating an object or array on the heap unless optimized away. |
| Short-lived object | Dies quickly and is often collected in young-generation work. |
| Long-lived object | Survives long enough to move toward older memory areas. |
| Allocation rate | How quickly new objects are created. |
| Shallow size | Memory used by one object itself. |
| Retained size | Memory that would become collectible if this object were removed. |

High allocation rate can cause GC pressure even when retained memory is low. A memory leak usually means retained memory grows because objects remain reachable.

## Escape Analysis Concepts

The JVM may determine that an object does not escape a method or thread. If so, it may remove allocation or replace the object with scalar values. This is called escape analysis and scalar replacement. These are runtime optimization concepts, not guarantees a developer should rely on blindly.

## Reachability

```text
GC roots -> live service -> cache map -> cached value -> large object graph
```

If a root can reach an object, the object is live from the collector's point of view. The object may be useless to the application and still not collectible.

Common accidental retention sources:

- static collections that only grow;
- caches without size or time bounds;
- listeners and callbacks that are never unregistered;
- `ThreadLocal` values left behind in pooled threads;
- classloaders retained by static state or background threads.

## Collection Concepts

| Collection idea | Meaning |
| --- | --- |
| Minor collection | Typically focuses on young memory where many objects die quickly. |
| Major collection | Involves older memory areas or broader heap work. |
| Stop-the-world pause | Application threads are paused for part of collector work. |
| Throughput goal | Maximize application work over time. |
| Pause-time goal | Keep individual pauses shorter or more predictable. |

Different garbage collectors make different tradeoffs. No collector is universally best, and changing collectors should be based on workload evidence.

## GC Logging And Flags

GC logs can show allocation pressure, pause behavior, heap occupancy, promotion, and collector activity. JVM flags control runtime behavior, but flags are part of the application experiment. Copying random flags can harm startup, memory headroom, pause behavior, or container operation.

Safe flag work means:

- define the goal first;
- capture a baseline;
- change one coherent group of flags;
- test under representative load;
- compare pauses, throughput, memory, errors, and startup;
- keep a rollback path.
