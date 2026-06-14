public class SingletonPatternDemo {
    public static void main(String[] args) {
        ApplicationSettings settings = ApplicationSettings.instance();
        System.out.println(settings.applicationName());
    }
}

class ApplicationSettings {
    private static final ApplicationSettings INSTANCE = new ApplicationSettings();

    private ApplicationSettings() {
    }

    static ApplicationSettings instance() {
        return INSTANCE;
    }

    String applicationName() {
        return "Learning App";
    }
}
