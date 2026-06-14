public class VariablesAndOperators {
    public static void main(String[] args) {
        String learner = "Franklin";
        int studyMinutes = 75;
        int sessionsPerWeek = 5;
        int weeklyMinutes = studyMinutes * sessionsPerWeek;

        System.out.println("Learner: " + learner);
        System.out.println("Weekly practice minutes: " + weeklyMinutes);
        System.out.println("More than 5 hours? " + (weeklyMinutes > 300));
    }
}
