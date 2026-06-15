# Releases, Upgrades, And Rollbacks

Helm tracks installed chart instances as releases.

## Install Concept

An install creates a release from a chart and a set of values. This repository does not run `helm install` against a cluster.

## Upgrade Concept

An upgrade changes an existing release by rendering the chart again with new chart files or new values.

## Rollback Concept

A rollback returns a release to a previous revision. It can help when an upgrade creates bad manifests or changes an application in an unsafe way.

## Uninstall Concept

Uninstall removes a release and the resources Helm manages for that release.

## Release History

Helm can show the revision history for a release. Production usage needs careful review because release history is not a substitute for release notes, backups, monitoring, and incident response.

## Local Learning Boundary

For this stage, prefer `helm lint` and `helm template` over any cluster-affecting command.
