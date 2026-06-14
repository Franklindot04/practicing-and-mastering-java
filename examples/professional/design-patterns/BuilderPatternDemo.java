public class BuilderPatternDemo {
    public static void main(String[] args) {
        ApiClientConfig config = new ApiClientConfig.Builder("https://api.example.test")
                .timeoutSeconds(10)
                .retries(3)
                .build();

        System.out.println(config);
    }
}

record ApiClientConfig(String baseUrl, int timeoutSeconds, int retries) {
    static class Builder {
        private final String baseUrl;
        private int timeoutSeconds = 5;
        private int retries = 1;

        Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        Builder timeoutSeconds(int timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            return this;
        }

        Builder retries(int retries) {
            this.retries = retries;
            return this;
        }

        ApiClientConfig build() {
            return new ApiClientConfig(baseUrl, timeoutSeconds, retries);
        }
    }
}
