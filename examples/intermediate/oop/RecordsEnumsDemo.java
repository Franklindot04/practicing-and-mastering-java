public class RecordsEnumsDemo {
    public static void main(String[] args) {
        Course course = new Course("Java Basics", Level.BEGINNER);
        System.out.println(course.title() + " is " + course.level());
    }
}

record Course(String title, Level level) {
}

enum Level {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}
