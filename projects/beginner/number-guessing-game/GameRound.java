public class GameRound {
    private final int secretNumber;
    private int attempts;

    public GameRound(int secretNumber) {
        this.secretNumber = secretNumber;
        this.attempts = 0;
    }

    public GuessResult guess(int value) {
        attempts++;

        if (value < secretNumber) {
            return GuessResult.TOO_LOW;
        }

        if (value > secretNumber) {
            return GuessResult.TOO_HIGH;
        }

        return GuessResult.CORRECT;
    }

    public int attempts() {
        return attempts;
    }
}

enum GuessResult {
    TOO_LOW,
    TOO_HIGH,
    CORRECT
}
