# Backend Values Structure

A beginner Java backend chart often needs a small set of value groups.

```yaml
image:
  repository: example/java-backend
  tag: local

service:
  port: 8080

container:
  port: 8080

spring:
  profile: kubernetes-local
```

## Image Values

`image.repository` identifies the image name. `image.tag` identifies the version.

Use placeholder image names in this repository. Learners must build or publish their own image before any real deployment.

## Ports

The container port is the port the application listens on inside the Pod. The Service port is the stable port other in-cluster clients use.

## Mapping Values To Templates

Templates read values and render Kubernetes fields. For example, a Deployment template might use image values to render:

```yaml
image: example/java-backend:local
```

## Common Mistakes

- Using `latest` without understanding what image is running
- Hiding important settings behind too many nested values
- Making every field configurable before there is a real need
