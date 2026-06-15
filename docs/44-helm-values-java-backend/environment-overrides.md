# Environment Overrides

Helm values can be split into defaults and overrides.

## Default Values

`values.yaml` should contain safe defaults. In this repository, defaults should be local-learning placeholders.

## Override Files

An override file such as `values-local.yaml` can adjust values for a local learning scenario.

Example command shape for rendering only:

```bash
helm template demo ./chart --values ./chart/values-local.yaml
```

Do not use install or upgrade commands against a real cluster in this stage.

## Profiles And Environments

A Spring profile can be passed through values and rendered into a ConfigMap.

Keep environment-specific differences clear:

- local learning values
- test values
- staging values later
- production values later and outside this beginner repo

## Common Mistakes

- Using one giant values file for every environment
- Committing production secrets in overrides
- Forgetting which override file was used for a rendered manifest
- Assuming a local override file is production-ready
