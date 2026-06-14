import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secretNumber = random.nextInt(MAX_NUMBER) + MIN_NUMBER;
        GameRound round = new GameRound(secretNumber);
        GuessResult result = null;

        System.out.println("I picked a number from " + MIN_NUMBER + " to " + MAX_NUMBER + ".");

        while (result != GuessResult.CORRECT) {
            int guess = readGuess(scanner);
            result = round.guess(guess);
            printResult(result, round.attempts());
        }

        scanner.close();
    }

    private static int readGuess(Scanner scanner) {
        System.out.print("Enter your guess: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private static void printResult(GuessResult result, int attempts) {
        if (result == GuessResult.TOO_LOW) {
            System.out.println("Too low.");
        } else if (result == GuessResult.TOO_HIGH) {
            System.out.println("Too high.");
        } else {
            System.out.println("Correct! Attempts: " + attempts);
        }
    }
}
