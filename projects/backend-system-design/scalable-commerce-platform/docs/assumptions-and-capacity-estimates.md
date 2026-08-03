# Assumptions And Capacity Estimates

Assumptions are explicit: catalogue reads dominate writes, checkout is write-sensitive, search can lag briefly, notification can degrade, inventory cannot oversell committed stock, payment calls require idempotency, and regional failover is rejected when RPO/RTO or data-residency assumptions are unsafe.

Capacity estimates use offered load, safe throughput, headroom, queue depth, partition count, hot-partition detection, storage growth, and cost bands. The simulator exposes these as typed reports so tests can verify the reasoning.
