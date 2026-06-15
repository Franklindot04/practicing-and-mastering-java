# Backend Chart Design Solutions

## Exercise 1

A reasonable outline includes `image.repository`, `image.tag`, `service.port`, `container.port`, `config.springProfile`, `probes.startupPath`, `probes.readinessPath`, `probes.livenessPath`, and `resources.requests`/`resources.limits`. Release metadata can use values such as `config.releaseVersion` and `config.commitSha`.

## Exercise 2

Spring profile and log level belong in a ConfigMap. A real database password and kubeconfig belong nowhere in this repository. A placeholder JWT secret can appear only in a clearly marked Secret example. Rendered manifests can expose values because templates produce plain YAML.
