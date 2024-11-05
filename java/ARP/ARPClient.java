import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ARPClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int PORT = 9999; // ARP port

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            // Construct ARP request (a simple string for demonstration)
            String request = "Who has IP 192.168.1.10?";
            byte[] requestData = request.getBytes();

            // Send ARP request to the server
            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, 
                    InetAddress.getByName(SERVER_ADDRESS), PORT);
            socket.send(requestPacket);
            System.out.println("Sent request: " + request);

            // Prepare to receive the response
            byte[] buffer = new byte[512]; // Buffer for incoming data
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket); // Receive ARP response

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Received response: " + response);

            socket.close(); // Close the socket
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
