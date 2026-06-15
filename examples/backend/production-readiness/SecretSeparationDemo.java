public class SecretSeparationDemo {
    public static void main(String[] args) {
        RuntimeSettings settings = RuntimeSettings.load();

        System.out.println("Safe public configuration:");
        System.out.println("serviceUrl=" + settings.serviceUrl());
        System.out.println("secretConfigured=" + settings.secretConfigured());
    }

    record RuntimeSettings(String serviceUrl, boolean secretConfigured) {
        static RuntimeSettings load() {
            String serviceUrl = read("EXAMPLE_SERVICE_URL", "http://localhost:8081");
            String apiToken = System.getenv("EXAMPLE_API_TOKEN");

            return new RuntimeSettings(serviceUrl, apiToken != null && !apiToken.isBlank());
        }

        private static String read(String key, String defaultValue) {
            String value = System.getenv(key);
            return value == null || value.isBlank() ? defaultValue : value;
        }
    }
}
