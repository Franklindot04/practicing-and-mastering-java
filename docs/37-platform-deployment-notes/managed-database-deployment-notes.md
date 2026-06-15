# Managed Database Deployment Notes

Deployed Java apps often connect to a managed database instead of local H2.

## Connection Planning

Document:

- Database type.
- Host and port.
- Database name.
- Username strategy.
- Secret source.
- TLS requirements.
- Network access rules.

Do not commit real connection strings when they include credentials.

## HTTPS And Domains

Public APIs should use HTTPS. Domains and certificates are usually configured through a platform, DNS provider, or infrastructure team.

Before real deployment, know:

- Public domain name.
- HTTPS requirement.
- Health endpoint URL.
- Smoke test URL.

## Cost And Quota Awareness

Managed databases and platforms may bill for uptime, storage, backups, logs, and traffic. Stop demo resources when you are done.
