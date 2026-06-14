# Multithreaded File Processor

Process many files using a controlled thread pool.

Requirements:

- Scan a directory.
- Submit file work to an executor.
- Collect success and failure results.
- Shut down the executor safely.
- Avoid shared mutable state where possible.
