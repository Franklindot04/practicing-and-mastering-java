# Pseudo-HCL for learning only.
# Do not run apply or destroy from this folder.

module "task_api_runtime" {
  source = "../modules/task-api-runtime-placeholder"

  application_name  = var.application_name
  environment_name  = var.environment_name
  container_image   = var.container_image
  runtime_port      = var.runtime_port
  health_check_path = var.health_check_path
}

module "task_api_database" {
  source = "../modules/database-placeholder"

  environment_name = var.environment_name
  database_name    = var.database_name
}

