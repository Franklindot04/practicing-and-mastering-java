# Debugging Routing And mTLS

Mesh issues often look like ordinary application failures until the request path is traced carefully.

## Routing Questions

- Which service version received the request?
- Was traffic split as expected?
- Did route labels match the intended workloads?
- Did a fallback or retry change the apparent behavior?
- Did the application log the same trace ID?

## mTLS Questions

- Did the caller and destination have expected workload identities?
- Was the connection encrypted?
- Was policy too strict or too broad?
- Did certificate rotation or trust changes happen recently?

## Diagnosis Flow

1. Confirm the application is healthy.
2. Confirm the destination dependency is healthy.
3. Compare application logs with mesh telemetry.
4. Check whether a recent policy or release changed routing.
5. Roll back the smallest safe change if user impact is active.

## Reminder

Do not debug by pasting real certificates, tokens, kubeconfig files, or production policy exports into notes or issues.

