import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate checkpoint = today.plusDays(7);
        Duration practiceSession = Duration.between(LocalTime.of(9, 0), LocalTime.of(10, 30));

        System.out.println("Today: " + today);
        System.out.println("Checkpoint: " + checkpoint);
        System.out.println("Practice minutes: " + practiceSession.toMinutes());
    }
}
