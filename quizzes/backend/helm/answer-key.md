# Helm Foundation Quiz Answer Key

## Helm Packaging Basics

1. A
2. A
3. A
4. Raw YAML teaches the Kubernetes objects Helm renders. Without that foundation, templates can hide important labels, selectors, ports, probes, and config references.
5. They are educational examples. They use placeholder images and secrets and are meant for linting/rendering, not production deployment.

## Charts, Values, And Templates

1. A
2. A
3. A
4. `image.repository` and `image.tag`.
5. The Service selector will not match the Pods, so traffic will not route to them.
6. Too many values can hide the Kubernetes shape and make the chart harder to reason about.

## Operations, Upgrades, And Rollbacks

1. A
2. A
3. A
4. Image name/tag, labels/selectors, ports, ConfigMap/Secret references, probe paths, resource requests/limits, and namespace assumptions.
5. Database migrations, external side effects, or secrets/config managed outside the chart.
6. Real credentials must not be committed. They can leak through Git history, reviews, rendered manifests, and local copies.
