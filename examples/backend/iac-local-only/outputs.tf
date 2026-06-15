output "runtime_summary" {
  description = "Non-secret summary of the modeled Java backend runtime."
  value       = module.java_backend_runtime.runtime_summary
}

output "health_check_url_hint" {
  description = "Local-only hint showing how app port and health path relate."
  value       = "http://localhost:${var.runtime_port}${var.health_check_path}"
}

