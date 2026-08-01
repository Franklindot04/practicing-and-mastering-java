# Failure Analysis And Design Quiz

## 1. Unknown Payment Outcome

An order service times out after calling payment. Payment later reports that it charged the card. Analyze the failure and propose a safer retry/status design.

## 2. Split Brain

A five-node cluster partitions into groups of two and three. Both sides can still serve local clients. Which side, if any, should accept authoritative writes, and what should happen after healing?

## 3. Clock-Skew Lease

A scheduler uses wall-clock expiry to decide which node owns a job. One node pauses and another clock is ahead. Describe the risk and propose a safer design.

## 4. Replica Promotion

A primary fails after accepting a write that has not reached one replica. The lagging replica is promoted. What can go wrong, and what checks would reduce risk?

## 5. Consistent Hashing Review

A team adds nodes weekly and complains that modulo hashing moves too much data. Compare modulo hashing and consistent hashing, including one limitation of consistent hashing.
