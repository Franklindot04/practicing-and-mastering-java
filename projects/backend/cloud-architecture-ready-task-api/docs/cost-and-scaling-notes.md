# Cost And Scaling Notes

## Scaling

More API instances can handle more concurrent requests, but each instance may open database connections. Scaling the backend without protecting the database can move the bottleneck.

## Cost Risks

- Too many always-on instances.
- Verbose logs retained too long.
- Database size growth.
- Backup retention growth.
- Queue backlog and worker scaling if async jobs are added.
- Data transfer from large responses or file downloads.

## Review Habit

For every new architecture component, ask what metric shows healthy use, what limit prevents runaway cost, and what alert tells a learner to investigate.

