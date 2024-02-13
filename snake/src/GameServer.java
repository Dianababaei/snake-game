import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;

public class GameServer {
    public static final int PORT = 8888;
    private Map<String, Player> waitingPlayers = new HashMap<>();
    public static void main(String[] args) {
        new GameServer().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Player player = new Player(clientSocket, this);
                player.start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public synchronized void addWaitingPlayer(String name, Player player) {
        waitingPlayers.put(name, player);
    }

    public synchronized void removeWaitingPlayer(String name) {
        waitingPlayers.remove(name);
    }

    public synchronized List<String> getWaitingPlayers() {
        return new ArrayList<>(waitingPlayers.keySet());
    }

    public synchronized Player getWaitingPlayer(String name) {
        return waitingPlayers.get(name);
    }}