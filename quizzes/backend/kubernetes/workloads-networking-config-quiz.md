# Workloads, Networking, And Configuration Quiz

## Multiple Choice

1. Which object usually manages replicated Pods and rolling updates?
   - A. Deployment
   - B. Secret
   - C. Namespace
   - D. ConfigMap

2. What does a Service selector match?
   - A. Pod labels
   - B. Maven dependencies
   - C. Dockerfile comments
   - D. Git branches

3. Which Service type is the normal internal-only default?
   - A. ClusterIP
   - B. LoadBalancer
   - C. ExternalName only
   - D. Ingress

## Short Answer

4. Why should real secrets not be committed in `secret.example.yaml`?

5. What is the difference between a readiness probe and a liveness probe?

## Design Reading

6. A Deployment creates Pods with label `app: task-api`, but the Service selector is `app: task-api-v2`. What happens?

7. A Java API starts slowly and liveness checks begin after five seconds. What problem might this cause?
