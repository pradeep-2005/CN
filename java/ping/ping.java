import java.io.IOException;
import java.net.InetAddress;

public class ping {
    public static void main(String[] args) {
        String host = "www.google.com";
        try {
            InetAddress inet = InetAddress.getByName(host);
            if (inet.isReachable(5000)) {
                System.out.println(host + " is reachable.");
            } else {
                System.out.println(host + " is not reachable.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
