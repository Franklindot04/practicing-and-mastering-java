output "runtime_review_summary" {
  description = "Pseudo-output showing what reviewers might inspect."
  value = {
    application_name  = var.application_name
    environment_name  = var.environment_name
    runtime_port      = var.runtime_port
    health_check_path = var.health_check_path
  }
}

output "database_review_summary" {
  description = "Pseudo-output for database planning. This is not a real endpoint or secret."
  value = {
    database_name    = var.database_name
    environment_name = var.environment_name
  }
}

