# Kubernetes Workloads, Networking, And Configuration

This section explains the Kubernetes objects learners usually meet first when they begin running backend services: Pods, Deployments, Services, ConfigMaps, Secrets, labels, namespaces, probes, resources, and rolling updates.

These notes are educational. They do not create a real cluster, cloud load balancer, or production deployment.

## Start Here

1. [Pods, Deployments, And Services](pods-deployments-services.md)
2. [ConfigMaps, Secrets, And Environment Variables](configmaps-secrets-env.md)
3. [Labels, Selectors, And Namespaces](labels-selectors-namespaces.md)
4. [Health Probes And Resources](health-probes-resources.md)
5. [Rolling Updates Basics](rolling-updates-basics.md)

## What You Should Learn

- How a Deployment manages replicated Pods
- How Services route traffic to matching Pods
- How configuration can be separated from container images
- Why Secrets still need careful handling
- How labels and selectors connect objects
- How probes and resource settings affect runtime behavior
