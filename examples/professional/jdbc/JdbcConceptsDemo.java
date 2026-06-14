import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConceptsDemo {
    public static void main(String[] args) {
        DatabaseConfig config = DatabaseConfig.fromEnvironment();
        System.out.println("JDBC URL configured: " + !config.url().isBlank());
        System.out.println("Open connections with DriverManager when a driver is available.");
    }

    static Connection open(DatabaseConfig config) throws SQLException {
        return DriverManager.getConnection(config.url(), config.username(), config.password());
    }
}

record DatabaseConfig(String url, String username, String password) {
    static DatabaseConfig fromEnvironment() {
        return new DatabaseConfig(
                System.getenv().getOrDefault("APP_DB_URL", ""),
                System.getenv().getOrDefault("APP_DB_USER", ""),
                System.getenv().getOrDefault("APP_DB_PASSWORD", ""));
    }
}
