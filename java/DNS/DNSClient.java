import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int PORT = 53; // DNS port

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            // Construct DNS query (a simple string for demonstration)
            String query = "What is the IP for example.com?";
            byte[] queryData = query.getBytes();

            // Send DNS query to the server
            DatagramPacket queryPacket = new DatagramPacket(queryData, queryData.length, 
                    InetAddress.getByName(SERVER_ADDRESS), PORT);
            socket.send(queryPacket);
            System.out.println("Sent query: " + query);

            // Prepare to receive the response
            byte[] buffer = new byte[512]; // Buffer for incoming data
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket); // Receive DNS response

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Received response: " + response);

            socket.close(); // Close the socket
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
