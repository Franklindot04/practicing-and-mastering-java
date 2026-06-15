variable "environment_name" {
  description = "Name of the learning environment. Use placeholder values such as local or dev."
  type        = string
  default     = "local"
}

variable "application_name" {
  description = "Logical Java backend application name."
  type        = string
  default     = "task-api"
}

variable "runtime_port" {
  description = "Port the Java backend would listen on in a runtime environment."
  type        = number
  default     = 8080
}

variable "health_check_path" {
  description = "HTTP path a runtime platform could use for health checks."
  type        = string
  default     = "/actuator/health"
}

variable "java_version" {
  description = "Java runtime version expected by the backend."
  type        = string
  default     = "17"
}

variable "container_image" {
  description = "Placeholder image reference only. Do not put real private registry credentials here."
  type        = string
  default     = "example/task-api:local"
}

