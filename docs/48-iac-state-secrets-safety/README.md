# IaC State, Secrets, And Safety

Infrastructure as Code becomes risky when learners treat state, plans, and credentials like ordinary notes. This section explains the safety model before the learning path moves toward real infrastructure.

The examples in this repository are educational and local-only. Do not commit state files, saved plan files, provider credentials, real secrets, kubeconfig files, or generated provider directories.

## Learning Goals

- Explain why state matters.
- Compare local state and remote state at a concept level.
- Understand why state and plans can expose sensitive data.
- Separate variables from secrets.
- Use safe local validation habits.
- Recognize common state and secret mistakes.

## Recommended Order

1. Read [State safety](state-safety.md).
2. Read [Secrets and provider credentials](secrets-and-provider-credentials.md).
3. Read [Local-only validation](local-only-validation.md).
4. Read [Common IaC safety mistakes](common-iac-safety-mistakes.md).

