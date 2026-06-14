import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExceptionsFilesDemo {
    public static void main(String[] args) {
        Path path = Path.of("java-practice-note.txt");

        try {
            Files.writeString(path, "Practice Java with small examples.");
            String text = Files.readString(path);
            System.out.println(text);
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.out.println("File operation failed: " + ex.getMessage());
        }
    }
}
