# Number Guessing Game Solution Walkthrough

## What The Project Does

The Number Guessing Game chooses a random number from 1 to 100. The player guesses until they find the number. After each guess, the program says whether the guess was too low, too high, or correct, and it counts the number of attempts.

## Main Java Concepts Used

- Random number generation with `Random`
- Console input with `Scanner`
- Loops that continue until a condition is met
- Conditionals for comparing numbers
- Classes for separating game logic from input/output
- An enum for the possible guess results

## Important Classes And Methods

- `NumberGuessingGame` runs the console program.
- `GameRound` stores the secret number and attempt count.
- `guess` compares one guess with the secret number.
- `attempts` returns how many guesses have been made.
- `GuessResult` represents `TOO_LOW`, `TOO_HIGH`, and `CORRECT`.

## Step-By-Step Logic

1. Set the smallest and largest possible numbers.
2. Generate a random secret number.
3. Create a `GameRound` with the secret number.
4. Tell the player the range.
5. Keep asking for guesses while the result is not correct.
6. For each guess, increase the attempt count.
7. Compare the guess with the secret number.
8. Print a hint or the final success message.

## Common Beginner Mistakes

- Forgetting that `random.nextInt(100)` creates numbers from 0 to 99.
- Not adding 1 when the intended range is 1 to 100.
- Forgetting to update the loop condition after each guess.
- Counting attempts only when the answer is correct.
- Putting comparison logic directly inside the input loop, which makes it harder to test.

## Possible Improvements

- Let the player choose an easy, medium, or hard range.
- Add a maximum number of guesses.
- Ask whether the player wants to play again.
- Track the best score during one program run.
- Give a warmer hint when the player is very close.

## Reflection Questions

1. Why does `GameRound` store the attempt count instead of using only a local variable in `main`?
2. What changes would be needed to support a range from 1 to 500?
3. How does the enum make the three possible results easier to read?
4. What part of this project is easiest to test without using `Scanner`?
