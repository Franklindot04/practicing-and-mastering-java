public class RuntimeConfigDemo {
    public static void main(String[] args) {
        RuntimeConfig config = RuntimeConfig.load();
        System.out.println(config);
    }

    record RuntimeConfig(int port, String profile) {
        static RuntimeConfig load() {
            return new RuntimeConfig(parsePort(read("APP_PORT", "8080")), read("APP_PROFILE", "local"));
        }

        private static String read(String key, String fallback) {
            String value = System.getenv(key);
            return value == null || value.isBlank() ? fallback : value;
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
