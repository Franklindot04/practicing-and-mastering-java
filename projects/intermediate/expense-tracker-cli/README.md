# Expense Tracker CLI

A command-line expense tracker that stores expenses in memory while the program runs. Each expense has a description, amount, category, and date.

## Concepts Practiced

- Classes and encapsulation
- `ArrayList` and `List`
- `BigDecimal` for money
- `LocalDate` for dates
- Console input with `Scanner`
- Simple validation
- Filtering and totals

## Files

```text
Expense.java
ExpenseTracker.java
ExpenseTrackerApp.java
```

## Compile

From the repository root:

```bash
javac projects/intermediate/expense-tracker-cli/Expense.java projects/intermediate/expense-tracker-cli/ExpenseTracker.java projects/intermediate/expense-tracker-cli/ExpenseTrackerApp.java
```

## Run

```bash
java -cp projects/intermediate/expense-tracker-cli ExpenseTrackerApp
```

## Example Usage

```text
1. Add expense
2. List expenses
3. Filter by category
4. Show total spending
5. Exit
Enter choice: 1
Description: Lunch
Amount: 12.50
Category: Food
Date (YYYY-MM-DD): 2026-06-14
Expense added.
```

## Possible Improvements

- Show monthly totals.
- Save and load expenses from a file.
- Add editing and deleting.
- Sort expenses by date or amount.
- Export expenses to CSV.
