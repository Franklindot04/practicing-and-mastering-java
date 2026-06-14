import java.util.List;

public class LongMethodRefactoringDemo {
    public static void main(String[] args) {
        List<Double> scores = List.of(90.0, 75.0, 82.0);
        System.out.println(buildSummary(scores));
    }

    private static String buildSummary(List<Double> scores) {
        double average = calculateAverage(scores);
        String grade = gradeFor(average);
        return "Average: " + average + ", grade: " + grade;
    }

    private static double calculateAverage(List<Double> scores) {
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        return total / scores.size();
    }

    private static String gradeFor(double average) {
        return average >= 80 ? "Strong" : "Review";
    }
}
