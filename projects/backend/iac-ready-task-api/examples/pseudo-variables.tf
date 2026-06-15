variable "environment_name" {
  description = "Placeholder environment name such as local, dev, test, or staging."
  type        = string
}

variable "application_name" {
  description = "Logical task API name."
  type        = string
  default     = "task-api"
}

variable "container_image" {
  description = "Placeholder image only. Do not include private registry credentials."
  type        = string
  default     = "example/task-api:local"
}

variable "runtime_port" {
  description = "Application port."
  type        = number
  default     = 8080
}

variable "health_check_path" {
  description = "Health endpoint path."
  type        = string
  default     = "/actuator/health"
}

variable "database_name" {
  description = "Placeholder logical database name. Do not include real connection strings."
  type        = string
  default     = "task_api_learning"
}

