# Operational Complexity

A service mesh adds infrastructure that must be operated like any other critical platform component.

## New Responsibilities

- Proxy version management.
- Control plane health monitoring.
- Policy review and rollout.
- Certificate and identity lifecycle.
- Resource overhead planning.
- Debugging request paths that include proxies.
- Coordinating mesh upgrades with application releases.

## Cost Of Complexity

Every sidecar, policy, and control-plane component can become part of an incident. Teams need enough observability and ownership to explain whether a failure is in the application, proxy, policy, network, dependency, or platform.

## Useful Habit

For every mesh feature, write down the operational owner, rollback path, and signal that proves it is working.

