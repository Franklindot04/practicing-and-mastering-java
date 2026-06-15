# Install, Upgrade, And Rollback

## helm install Concept

`helm install` creates a release in a Kubernetes cluster. This repository does not use it during Stage 13 validation.

## helm upgrade Concept

`helm upgrade` changes an existing release. It might change image tags, configuration values, templates, resources, or labels.

## helm rollback Concept

`helm rollback` returns a release to a previous revision. Rollback can help with a bad chart change, but it does not undo every external effect.

## helm uninstall Concept

`helm uninstall` removes a release and the resources Helm manages for it.

## helm history Concept

`helm history` shows release revisions. History helps explain what changed, but production teams still need release notes and monitoring.

## Production Reminder

Production Helm operations require review, access control, secret management, rollback planning, observability, and incident response.
