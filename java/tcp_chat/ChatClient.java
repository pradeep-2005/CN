import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            System.out.println("Connected to the server.");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Client: ");
                message = consoleInput.readLine();
                output.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed.");
                    break;
                }

                message = input.readLine();
                System.out.println("Server: " + message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Server has closed the connection.");
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
