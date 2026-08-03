# Dependency Degradation Runbook

Confirm whether the dependency is optional or required for checkout. For optional dependencies, continue core checkout and record degraded status. For required dependencies, prefer bounded rejection over slow failure.
