public class DatabaseUrlPlaceholderDemo {
    public static void main(String[] args) {
        String placeholder = "jdbc:postgresql://db-host.example:5432/appdb";
        System.out.println("Placeholder looks safe: " + looksLikeSafePlaceholder(placeholder));
    }

    static boolean looksLikeSafePlaceholder(String url) {
        if (url == null || url.isBlank()) {
            return false;
        }
        String lower = url.toLowerCase();
        return lower.startsWith("jdbc:")
                && !lower.contains("password=")
                && !lower.contains("secret")
                && !lower.contains("@real-");
    }
}
