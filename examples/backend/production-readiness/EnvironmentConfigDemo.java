public class EnvironmentConfigDemo {
    public static void main(String[] args) {
        AppConfig config = AppConfig.fromEnvironment();

        System.out.println("Application name: " + config.applicationName());
        System.out.println("Port: " + config.port());
        System.out.println("Demo mode: " + config.demoMode());
    }

    record AppConfig(String applicationName, int port, boolean demoMode) {
        static AppConfig fromEnvironment() {
            String name = read("APP_NAME", "production-readiness-demo");
            int port = parsePort(read("APP_PORT", "8080"));
            boolean demoMode = Boolean.parseBoolean(read("APP_DEMO_MODE", "true"));
            return new AppConfig(name, port, demoMode);
        }

        private static String read(String key, String defaultValue) {
            String value = System.getenv(key);
            return value == null || value.isBlank() ? defaultValue : value;
        }

        private static int parsePort(String value) {
            try {
                int port = Integer.parseInt(value);
                return port > 0 && port <= 65535 ? port : 8080;
            } catch (NumberFormatException ex) {
                return 8080;
            }
        }
    }
}
