# Object Storage Architecture

```text
Client -> Backend Service -> Object Storage
                    |
                    v
             Managed Database
```

## What It Shows

The backend stores file-like content in object storage and metadata in a managed database. This is common for exports, uploaded attachments, generated reports, or media.

## Tradeoffs

- Keeps large binary content out of the relational database.
- Lets metadata stay queryable.
- Needs access control and lifecycle rules.
- Requires a cleanup plan when metadata or objects are deleted.

## Intentionally Simplified

This example avoids real bucket names, public URLs, provider policies, CDN configuration, and signed URL details.

