import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DNSServer {
    private static final int PORT = 53; // Standard DNS port

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("DNS Server is running...");

            byte[] buffer = new byte[512]; // Buffer for incoming data

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive DNS query

                String query = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received query: " + query);

                // Here you would typically parse the DNS query
                // For this example, we'll assume a static response
                String response = "DNS Response: 192.168.1.1"; // Example IP address

                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(responsePacket); // Send DNS response
                System.out.println("Sent response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
