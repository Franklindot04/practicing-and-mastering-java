# When Not To Add Service Mesh

The task API does not need a service mesh while it is a single learning service with simple communication paths.

## Do Not Add A Mesh Just Because

- The app already uses Kubernetes.
- A diagram looks more advanced with sidecars.
- Observability basics are missing.
- Service boundaries are unclear.
- The team cannot explain the operational cost.
- The current problem is application design, database modeling, or API behavior.

## Better Next Steps

- Improve tests.
- Clarify API contracts.
- Add useful application metrics.
- Practice deployment and rollback.
- Review cloud architecture tradeoffs.
- Document dependency behavior before introducing dependencies.

## Readiness Rule

Only add a mesh when service-to-service communication problems are real, repeated, observable, and worth centralizing.

