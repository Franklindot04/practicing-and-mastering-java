# kubectl And Manifests

Kubernetes objects are commonly written as YAML manifests. A manifest describes an object such as a Deployment, Service, ConfigMap, or Secret.

## Manifest Basics

Most manifests include:

- `apiVersion`: which Kubernetes API version the object uses
- `kind`: what type of object it is
- `metadata`: name, namespace, and labels
- `spec`: the desired behavior for that object

Example shape:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: example-config
data:
  APP_MODE: local-learning
```

## kubectl Concept

`kubectl` is the command-line tool used to talk to the Kubernetes API server.

Common command categories:

- View resources
- Apply manifests
- Describe objects
- Read logs
- Check rollout status
- Delete local learning resources

This stage does not require running real cluster commands. If you experiment locally later, prefer a disposable local learning namespace.

## Common Manifest Mistakes

- Wrong indentation
- Mismatched labels and selectors
- Using a Secret as if it were automatically encrypted everywhere
- Forgetting the namespace
- Copying image names that do not exist locally
- Treating demo manifests as production-ready
