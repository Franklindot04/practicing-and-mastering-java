# Values Overrides And Troubleshooting

## Values Override Files

Values override files let you change chart behavior without editing templates.

Example:

```bash
helm template demo ./chart --values ./chart/values-local.yaml
```

## Common Helm Issues

- YAML indentation errors after rendering
- Missing values referenced by templates
- Wrong image repository or tag
- Service selectors not matching Pod labels
- Secret examples accidentally treated like real secret management
- Override files committed with sensitive values

## Troubleshooting Flow

1. Run `helm lint`.
2. Render with `helm template`.
3. Inspect the rendered YAML.
4. Compare values with templates.
5. Check labels, selectors, image, ports, probes, and resources.

## Safe Local Practice

For this stage, troubleshooting should stay render-only and local. Do not use a real cluster to discover beginner chart mistakes.
