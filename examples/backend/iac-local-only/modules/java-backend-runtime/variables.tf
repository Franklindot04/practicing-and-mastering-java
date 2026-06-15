variable "application_name" {
  description = "Logical application name."
  type        = string
}

variable "environment_name" {
  description = "Learning environment name."
  type        = string
}

variable "runtime_port" {
  description = "Application runtime port."
  type        = number
}

variable "health_check_path" {
  description = "Health endpoint path."
  type        = string
}

variable "java_version" {
  description = "Java runtime version."
  type        = string
}

variable "container_image" {
  description = "Placeholder container image reference."
  type        = string
}

