import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    public static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        new GameClient().start();
    }

    public void start() {
        try (Socket serverSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
             PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true)) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            out.println("JOIN " + name);

            String serverResponse = in.readLine();
            if ("ACCEPTED".equals(serverResponse)) {
                System.out.println("Connected to the server.");
            } else {
                System.out.println("Could not connect to the server.");
            }

            // Add your game logic here

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}