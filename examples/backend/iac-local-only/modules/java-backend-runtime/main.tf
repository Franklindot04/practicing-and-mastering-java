resource "terraform_data" "runtime_summary" {
  input = {
    application_name  = var.application_name
    environment_name  = var.environment_name
    runtime_port      = var.runtime_port
    health_check_path = var.health_check_path
    java_version      = var.java_version
    container_image   = var.container_image
  }
}

