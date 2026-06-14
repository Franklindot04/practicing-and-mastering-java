import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClientDemo {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 5050), 3_000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(reader.readLine());
        }
    }
}
