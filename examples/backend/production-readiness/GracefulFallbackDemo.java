public class GracefulFallbackDemo {
    public static void main(String[] args) {
        RecommendationClient client = new RecommendationClient(false);
        String message = client.fetchSuggestion();

        System.out.println(message);
    }

    static class RecommendationClient {
        private final boolean externalServiceAvailable;

        RecommendationClient(boolean externalServiceAvailable) {
            this.externalServiceAvailable = externalServiceAvailable;
        }

        String fetchSuggestion() {
            if (!externalServiceAvailable) {
                return "Fallback suggestion: review your highest-priority task first.";
            }
            return "External suggestion: prioritize task 42.";
        }
    }
}
