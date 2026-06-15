# Helm Overview

Helm helps package Kubernetes applications. A single backend service might need a Deployment, Service, ConfigMap, Secret placeholder, probes, and resource settings. Helm puts those related files into a chart.

## Why Helm Exists

Raw Kubernetes YAML is useful for learning, but repeated copy-paste becomes hard to maintain. Helm helps with:

- Grouping related manifests
- Reusing a chart with different values
- Rendering templates before applying them
- Tracking release history
- Upgrading and rolling back an installed release

## Key Terms

- Chart: a package of Kubernetes templates and default values
- Template: a manifest file with placeholders and logic
- Values: configuration passed into templates
- Release: an installed instance of a chart
- Repository: a place where packaged charts can be published and downloaded

## Local-Only Scope

The examples in this repository are for reading, linting, and rendering locally. They are not production-ready and should not be installed into shared or real clusters.
