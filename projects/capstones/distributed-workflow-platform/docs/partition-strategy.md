# Partition Strategy

Tasks carry partition keys and workers own a partition in this model. The partition report identifies hot partitions and recommends rebalancing when one partition dominates local work.

## Purpose

Partitioning demonstrates that workflow systems can bottleneck on routing decisions even when total worker count looks healthy. A hot partition can delay work while other partitions sit idle.

## Trade-Offs

Stable partitioning improves locality and ordering but can create hot spots. Dynamic rebalancing improves utilization but can make ownership, ordering, and recovery more complex. The capstone uses a simple threshold so learners can focus on recognizing the symptom.

## Operational Implications

Monitor task count by partition, oldest task age, worker ownership, and rejected admissions. A rebalance recommendation should lead to diagnosis, not automatic movement, because moving work can affect ordering and duplicate execution risk.
