# Operational Troubleshooting Quiz

Scenario:

Users report that notification emails arrive 45 minutes late. Producers show successful publishes. Dead-letter volume is low. Consumer logs show many dependency timeouts.

Questions:

1. Which health signals would you inspect first?
2. Is this more likely a producer problem or consumer/dependency problem?
3. What does queue depth or consumer lag help reveal?
4. Why can retry volume make the delay worse?
5. Which fields should appear in structured logs?
6. What mitigation could be safe before code changes?
7. What follow-up work should be created after the incident?
