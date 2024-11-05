import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class TracerouteSimulation {
    public static void main(String[] args) {
        String host = "example.com";
        int maxHops = 30;
        int port = 33434;

        try {
            InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println("Traceroute to " + host);

            for (int ttl = 1; ttl <= maxHops; ttl++) {
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(3000);
                socket.setTrafficClass(ttl);

                byte[] buf = new byte[32];
                DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, port);

                long startTime = System.currentTimeMillis();
                socket.send(packet);

                try {
                    socket.receive(packet);
                    long endTime = System.currentTimeMillis();
                    InetAddress router = packet.getAddress();
                    System.out.println("Hop " + ttl + ": " + router + " in " + (endTime - startTime) + " ms");
                    
                    if (router.equals(inetAddress)) {
                        System.out.println("Reached destination.");
                        break;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("Hop " + ttl + ": Request timed out");
                }

                socket.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
