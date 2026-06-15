import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PasswordHashingConceptDemo {
    public static void main(String[] args) {
        String demoSalt = "demo-only-salt";
        String hash = educationalHash("demo-password", demoSalt);

        System.out.println("Stored value is a hash, not the password:");
        System.out.println(hash);
    }

    static String educationalHash(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest((salt + ":" + password).getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is unavailable", exception);
        }
    }
}
