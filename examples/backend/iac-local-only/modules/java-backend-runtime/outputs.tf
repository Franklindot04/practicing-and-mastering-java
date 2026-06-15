output "runtime_summary" {
  description = "Non-secret runtime metadata recorded for learning."
  value       = terraform_data.runtime_summary.output
}

