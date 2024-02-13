import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    private Socket socket;
    private GameServer server;
    private BufferedReader in;
    private PrintWriter out;
    private String playerName;

    public Player(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error setting up stream: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    break;
                }

                String[] parts = input.split(" ");
                if (parts.length == 0) {
                    continue;
                }

                String command = parts[0];
                if ("JOIN".equals(command) && parts.length == 2) {
                    playerName = parts[1];
                    server.addWaitingPlayer(playerName, this);
                    out.println("ACCEPTED");
                }

                // Add other commands for your game logic
            }
        } catch (IOException e) {
            System.err.println("Error reading from socket: " + e.getMessage());
        } finally {
            if (playerName != null) {
                server.removeWaitingPlayer(playerName);
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}