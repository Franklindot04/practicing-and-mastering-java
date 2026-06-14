public class MethodsArraysStrings {
    public static void main(String[] args) {
        int[] scores = {90, 85, 72, 100};
        System.out.println("Average: " + average(scores));

        String topic = "Java basics";
        System.out.println("Uppercase topic: " + topic.toUpperCase());
        System.out.println("Contains Java? " + topic.contains("Java"));
    }

    public static double average(int[] values) {
        int total = 0;

        for (int value : values) {
            total += value;
        }

        return (double) total / values.length;
    }
}
