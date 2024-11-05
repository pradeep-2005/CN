import java.io.*;
import java.net.*;

public class WebpageDownloader {
    public static void main(String[] args) {
        String urlString = "http://example.com"; // Replace with the URL you want to download
        String outputFile = "downloaded_page.html";

        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
    }

            reader.close();
            writer.close();
            System.out.println("Webpage downloaded successfully to " + outputFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

