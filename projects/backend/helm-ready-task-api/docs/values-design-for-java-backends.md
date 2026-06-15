# Values Design For Java Backends

Good values files expose settings learners need to reason about without hiding Kubernetes basics.

Useful value groups:

- `image`: repository, tag, pull policy
- `service`: type and port
- `container`: container port
- `config`: Spring profile and release metadata
- `secretExample`: placeholder values only
- `probes`: health paths
- `resources`: CPU and memory requests and limits

Avoid adding provider-specific settings, real registry credentials, cloud keys, or production database connection strings.
