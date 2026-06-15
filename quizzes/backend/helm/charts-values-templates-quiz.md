# Charts, Values, And Templates Quiz

## Multiple Choice

1. Which file stores chart metadata?
   - A. `Chart.yaml`
   - B. `values.yaml`
   - C. `README.md`
   - D. `pom.xml`

2. Which folder contains Helm template files?
   - A. `templates/`
   - B. `target/`
   - C. `.git/`
   - D. `src/test/`

3. What should `values.yaml` contain in this repository?
   - A. Safe placeholder defaults
   - B. Real production passwords
   - C. Kubeconfig data
   - D. Cloud access keys

## Template Reading

4. In `{{ .Values.image.repository }}:{{ .Values.image.tag }}`, which two values control the rendered image?

5. A Service selector renders `app: api`, but the Deployment Pod label renders `app: task-api`. What is the likely problem?

## Short Answer

6. Why can too many configurable values make a beginner chart harder to understand?
