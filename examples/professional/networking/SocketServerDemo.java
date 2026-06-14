import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            System.out.println("Server listening on port 5050");
            try (Socket client = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {
                writer.println("Hello from the server");
            }
        }
    }
}
