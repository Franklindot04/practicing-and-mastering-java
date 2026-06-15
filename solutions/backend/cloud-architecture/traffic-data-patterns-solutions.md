# Traffic And Data Patterns Solutions

## Exercise 1

One acceptable diagram:

```text
Client -> DNS -> Load Balancer -> Task API Instances -> Managed Database
```

Health checks let the load balancer avoid unhealthy task API instances.

## Exercise 2

A queue is usually the better first fit for a 30-second report export because the work is slow and can run in the background. A cache helps repeated reads, not long-running new work.

The queue introduces failure modes such as backlog growth, duplicate processing, and jobs that need retry or dead-letter handling.

## Exercise 3

Generated files can go into object storage, while database rows store metadata such as report ID, owner, status, object key, size, and creation time. Cleanup must handle both metadata and objects so orphaned files do not accumulate.

