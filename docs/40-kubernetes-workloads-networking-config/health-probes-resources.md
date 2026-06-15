# Health Probes And Resources

Kubernetes can check whether a container has started, is alive, and is ready to receive traffic. These checks are especially useful for backend services.

## Readiness Probe

A readiness probe tells Kubernetes whether a Pod should receive traffic.

For a Java API, readiness should usually mean the application is ready to answer requests, not merely that the process exists.

## Liveness Probe

A liveness probe tells Kubernetes whether the container should be restarted.

Use liveness carefully. A probe that is too aggressive can restart a slow-starting or temporarily busy Java app.

## Startup Probe

A startup probe gives slow-starting applications extra time before liveness checks begin.

This can help Java applications that need time for class loading, warmup, or local demo database initialization.

## Resource Requests And Limits

Resource requests describe what the workload expects to need. Limits describe the maximum it is allowed to use.

Beginner mental model:

- Request: scheduling signal
- Limit: enforcement boundary

Java applications need thoughtful memory settings. A memory limit that is too low can cause restarts even when the code is correct.

## Common Mistakes

- Pointing probes at an endpoint that requires authentication
- Using the same endpoint for every probe without thinking
- Setting very short probe delays for a Java app
- Omitting resource requests entirely
- Setting memory limits without considering JVM behavior
