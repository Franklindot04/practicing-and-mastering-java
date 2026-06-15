# Labels, Selectors, And Namespaces

## Labels

Labels are key-value pairs attached to Kubernetes objects. They help identify and group related resources.

Example label ideas:

- `app: task-api`
- `component: backend`
- `stage: learning`

## Selectors

Selectors find objects with matching labels. Services use selectors to decide which Pods receive traffic.

If a Deployment creates Pods with `app: task-api`, a Service selector should match that same label if it is meant to route to those Pods.

## Namespaces

Namespaces create logical boundaries inside a cluster. They are useful for grouping related resources and avoiding name collisions.

For local learning, a namespace like `learnjava-kubernetes` makes cleanup easier because all demo resources live in one place.

## Common Mistakes

- Mismatching Service selectors and Pod labels
- Reusing the same object names across namespaces without noticing
- Forgetting to include `metadata.namespace`
- Treating namespaces as a complete security boundary by themselves
