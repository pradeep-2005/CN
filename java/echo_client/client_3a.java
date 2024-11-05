import java.io.*;
import java.net.*;

public class client_3a {
    public static void main(String[] args) {
        String hostname = "localhost"; // or the server's IP address
        int port = 1234;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server");

            String userInput;
            while (true) {
                System.out.print("Enter message for server: ");
                userInput = consoleInput.readLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Disconnecting from server...");
                    break;
                }

                output.println(userInput); // Send message to the server
                System.out.println("Server replied: " + input.readLine()); // Print server response
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
