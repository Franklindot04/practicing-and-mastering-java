# Common Spring Security Mistakes

Security mistakes often come from trying to move too fast.

## Disabling Security Without A Plan

Disabling CSRF, CORS, or authentication can be acceptable in narrow demos, but only when you understand why and document the tradeoff.

## Hardcoding Real Secrets

Never commit real passwords, API tokens, JWT secrets, private keys, database credentials, or `.env` files.

## Confusing 401 And 403

Use `401 Unauthorized` when the request is not authenticated.

Use `403 Forbidden` when the user is authenticated but not allowed.

## Returning Too Much Error Detail

Avoid leaking stack traces, SQL messages, password hash details, token internals, or user lookup details.

## Custom Token Code Too Early

JWT and refresh-token systems are easy to implement poorly. Use proven libraries and learn simpler protected route flows first.

## Trusting The Client

Do not trust client-sent roles, ids, ownership claims, or prices without server-side checks.

## Production Warning

Educational security examples are intentionally small. Production systems also need hardened configuration, secret management, logging, monitoring, dependency updates, threat modeling, and careful review.
