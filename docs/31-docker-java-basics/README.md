# Docker For Java Basics

Docker packages an application and the files it needs into an image. A container is a running instance of that image.

For Java backend learners, Docker is useful because it makes runtime setup more repeatable. It does not automatically make an application production-ready.

## Image Versus Container

- Image: a built package containing application files and runtime instructions.
- Container: a running process created from an image.

You can create many containers from one image.

## Why Java Apps Use Containers

- Repeatable runtime environment.
- Easier local demos.
- Clear build and run instructions.
- Better alignment between development and deployment environments.

This section is for local Docker learning. It does not introduce Kubernetes or cloud deployment.

## Study Order

1. [Dockerfile Basics](dockerfile-basics.md)
2. [Java Container Best Practices](java-container-best-practices.md)
