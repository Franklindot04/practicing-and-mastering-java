# Stream Processing Windows And Late Events

## Event Streams

An event stream is unbounded data. Stream processors filter, map, group, join, aggregate, and write new streams or state stores. Kafka Streams, Flink, and similar tools are implementations; the concepts apply more broadly.

Streams differ from simple queues because retained history, keyed state, and replay are central. A stream processor may rebuild state from input topics and changelogs.

## Stateless And Stateful Processing

Stateless processing transforms one record at a time, such as filtering cancelled orders or mapping an internal event to an analytics event. Stateful processing groups records, counts by key, joins streams, or maintains projections.

Stateful processing needs state stores, changelogs, snapshots, restore behavior, and capacity planning.

## Time

Event time is when the business event happened. Processing time is when the processor handled it. Ingestion time is when the broker accepted it. Late events happen when event time belongs to an earlier window but the processor observes the record later.

Window types:

- tumbling windows: fixed, non-overlapping windows
- hopping windows: fixed windows that overlap by hop interval
- session windows: activity-based windows separated by inactivity gaps

Watermarks estimate when enough data has arrived to close a time window. Late-event policy decides whether to update the old result, emit a correction, route to a late-events topic, or ignore after a grace period.

## Repartitioning And Joins

Grouping and joining often require records to be partitioned by the join or group key. Repartitioning adds network, storage, and operational cost. Bad keys can create hot partitions and slow state restoration.

## Stream Table Duality

A stream records changes over time. A table represents the latest value by key. A compacted topic can act as a changelog for a table-like view, while a stream of updates can rebuild that view through replay.
