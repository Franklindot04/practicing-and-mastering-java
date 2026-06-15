# Cloud Readiness Solutions

## Exercise 1

Checklist items should include external configuration, no committed secrets, health endpoint, logs, known port behavior, managed database plan, HTTPS/domain plan, smoke tests, and cost/quota awareness.

## Exercise 2

The app needs host, port, database name, username strategy, password or credential source, TLS requirements, and network access rules. Real credentials should come from the platform or secret manager, not Git. H2 is local/test learning storage and does not represent production database operations.

## Exercise 3

Before public exposure, know the domain, DNS routing, HTTPS certificate strategy, open ports, firewall/security group rules, health URL, and who owns incident response. Admin consoles, database ports, and secret-bearing endpoints should not be public.
