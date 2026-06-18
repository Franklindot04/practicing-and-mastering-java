# Coordination Solutions

## Exercise 1

The safest beginner answer is to make cleanup idempotent and data-driven, such as selecting records eligible for cleanup and marking them with conditional updates. If leadership is still needed, stale leaders should be prevented from writing late results.

## Exercise 2

The worker's lock lease expired during the pause, so another worker may now own the work. A fencing token lets storage reject writes from the old worker after a newer token has been issued.

