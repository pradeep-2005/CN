import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ARPServer {
    private static final int PORT = 9999; // Custom port for ARP

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("ARP Server is running...");

            byte[] buffer = new byte[512]; // Buffer for incoming data

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive ARP request

                String request = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received request: " + request);

                // Here you would typically parse the ARP request
                // For this example, we'll assume a static response
                String response = "ARP Response: 00:1A:2B:3C:4D:5E"; // Example MAC address

                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(responsePacket); // Send ARP response
                System.out.println("Sent response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
