# Number Guessing Game

A beginner-friendly command-line game where the computer chooses a number and the player guesses until they get it right.

## Concepts Practiced

- Random numbers
- Console input with `Scanner`
- Loops
- Conditionals
- Classes and methods
- Counting attempts

## Files

```text
NumberGuessingGame.java
GameRound.java
```

## Compile

From the repository root:

```bash
javac projects/beginner/number-guessing-game/GameRound.java projects/beginner/number-guessing-game/NumberGuessingGame.java
```

## Run

```bash
java -cp projects/beginner/number-guessing-game NumberGuessingGame
```

## Example Usage

```text
I picked a number from 1 to 100.
Enter your guess: 50
Too low.
Enter your guess: 75
Too high.
Enter your guess: 63
Correct! Attempts: 3
```

## Possible Improvements

- Add difficulty levels.
- Limit the number of guesses.
- Allow replay.
- Track the best score.
