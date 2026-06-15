# Terraform/OpenTofu Concepts Quiz

## Multiple Choice

1. What does a provider do?
   - A. Stores Java bytecode
   - B. Talks to a platform or service
   - C. Replaces state
   - D. Deletes pull requests

2. What is a variable?
   - A. An input to configuration
   - B. A generated state backup
   - C. A secret manager by default
   - D. A real cloud account

3. What is an output?
   - A. A selected value exposed by configuration
   - B. A provider credential
   - C. A command that always applies changes
   - D. A replacement for review

## Short Answer

4. Compare Terraform and OpenTofu at a beginner level.

5. Why should secrets not be ordinary committed variable values?

## HCL Reading

6. In this snippet, identify one variable and one output:

```hcl
variable "runtime_port" {
  type    = number
  default = 8080
}

output "health_check_url_hint" {
  value = "http://localhost:${var.runtime_port}/actuator/health"
}
```

