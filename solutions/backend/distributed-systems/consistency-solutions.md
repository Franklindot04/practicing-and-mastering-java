# Consistency Solutions

## Exercise 1

The task detail page and task list should usually provide read-after-write consistency for the user who created the task. The dashboard summary may be eventually consistent if the UI can tolerate a short delay.

## Exercise 2

Possible problems include users seeing a task in one screen but not in reports, managers making decisions from stale counts, and support teams misreading workflow status. Mitigations include last-updated timestamps, lag metrics, and keeping command decisions away from delayed projections.

