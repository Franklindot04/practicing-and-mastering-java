# Common Helm Mistakes

## Treating Templates As Magic

Helm does not make Kubernetes concepts disappear. You still need to understand Deployments, Services, ConfigMaps, Secrets, probes, resources, labels, and selectors.

## Not Inspecting Rendered YAML

Always inspect rendered manifests. Template syntax can be valid while the rendered Kubernetes object is wrong.

## Putting Real Secrets In Values

Do not commit real passwords, tokens, signing keys, cloud credentials, kubeconfig files, or registry credentials in chart values.

## Over-Templating

Not every field needs to be configurable. Too many values can make a beginner chart harder to understand.

## Reusing Production-Looking Examples Blindly

The examples in this repository are local-only learning material. Production Helm charts need deeper review, security controls, observability, release process, and operational ownership.
