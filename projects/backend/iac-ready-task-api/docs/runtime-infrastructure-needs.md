# Runtime Infrastructure Needs

A backend task API usually needs more than application code. IaC planning helps name those needs clearly before selecting a real platform.

## Runtime Questions

- What Java version does the app require?
- What port does it expose?
- What health endpoint should a platform check?
- How many instances are needed for the learning environment?
- Which environment variables are required?
- How should logs be collected?

## Relationship To Earlier Projects

The deployment-ready API introduces runtime configuration and health concepts. The Kubernetes-ready notes map those concepts into manifests. The Helm-ready notes show how chart values can make those settings reusable.

IaC planning asks what surrounds those runtime definitions: networks, databases, secrets, state, identity, and environment boundaries.

## Not Production-Ready

These notes do not prescribe a production platform. Real production infrastructure needs capacity planning, security review, incident response expectations, cost ownership, and team-specific standards.

