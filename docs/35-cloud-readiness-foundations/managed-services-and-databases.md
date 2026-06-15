# Managed Services And Databases

A managed service is operated by a platform or provider. The provider handles some operational work, such as backups, patches, availability, or scaling.

## Managed Database Concept

Instead of running a database inside your app process, a deployed backend often connects to a managed database.

The app usually needs:

- Database host.
- Port.
- Database name.
- Username.
- Password or credential.
- TLS requirements.

These values must be supplied safely at runtime.

## Object Storage Concept

Object storage stores files such as images, exports, logs, or backups. It is not the same as a relational database.

Common uses:

- User-uploaded files.
- Generated reports.
- Backups.
- Static assets.

Do not put object storage credentials in source control.

## Common Mistakes

- Treating local H2 settings as production database settings.
- Hard-coding managed database passwords.
- Logging full database URLs when they include credentials.
- Skipping backup and restore planning.
- Forgetting network access rules.
