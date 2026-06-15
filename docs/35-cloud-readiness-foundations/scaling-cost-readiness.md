# Scaling And Cost Readiness

Scaling means adjusting capacity to handle workload.

## Scaling Basics

- Vertical scaling: make one server/container bigger.
- Horizontal scaling: run more instances.
- Autoscaling: adjust instance count based on rules.

Before scaling, understand the bottleneck. More instances do not fix every problem.

## Application Considerations

Cloud-ready apps should avoid assuming:

- There is only one running instance.
- Local disk is permanent.
- In-memory state is shared between instances.
- Background jobs can run duplicated without consequences.

## Cost Awareness

Cloud resources cost money. Cost can come from:

- Compute runtime.
- Database size and uptime.
- Storage.
- Network traffic.
- Logs and metrics volume.

## Common Mistakes

- Leaving demo resources running.
- Overprovisioning before measuring.
- Logging too much.
- Ignoring free-tier limits or quotas.
- Scaling an app that still has hard-coded local-only settings.
