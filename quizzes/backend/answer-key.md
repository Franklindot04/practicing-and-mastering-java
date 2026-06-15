# Backend Java Foundation Quiz Answer Key

## HTTP And REST Quiz

1. A
2. B
3. C
4. C
5. A path variable identifies part of the URL path, such as `/tasks/8`. A query parameter adds optional filtering or sorting data, such as `/tasks?completed=false`.
6. Clients, caches, crawlers, and tools expect `GET` to be safe. Changing data from `GET` can cause surprising duplicate or accidental changes.
7. The resource is task `8`. `PUT` is reasonable because the client is replacing the resource at a known id, and repeating the same replacement should have the same final effect.

## Spring Boot Basics Quiz

1. B
2. C
3. A
4. B
5. Auto-configuration creates common Spring infrastructure from the dependencies and settings already present, reducing manual setup.
6. Extra concerns can hide the basic request-controller-service-response flow. Learn one resource first, then add database and security later.
7. `TaskService` is being injected. The controller can depend on a service boundary instead of creating service details itself, which makes behavior easier to test or replace.

## Backend Layering And Validation Quiz

1. B
2. A
3. C
4. B
5. Thin controllers keep HTTP mapping separate from business decisions. The service can then be tested without starting a server.
6. Useful fields include `status`, `message`, `path`, `timestamp`, and optional `details`.
7. Internal models may expose fields clients should not see and make the public API harder to change safely.
8. It belongs in the service layer. It validates business input and coordinates saving plus mapping to a response DTO.
