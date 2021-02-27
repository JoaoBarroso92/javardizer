package org.academiadecodigo.asynctomatics56.javardizer;




import java.io.Closeable;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Map<Socket, String> connectedUsers;
    private final int NUM_THREADS = 200;
    ClientHandler clientHandler;

    public Server(int port) {

        connectedUsers = new HashMap<>();

        try {
            openConnection(port);
            while (true) {
                listen(serverSocket);
                createNewHandler(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cleanup(clientSocket);
            cleanup(serverSocket);
        }
    }

    private void openConnection(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void createNewHandler(Socket clientSocket) throws IOException {

        System.out.println("Creating new Handler for " + clientSocket);
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        clientHandler = new ClientHandler(clientSocket, connectedUsers);
        executor.submit(clientHandler);


    }

    private void listen(ServerSocket serverSocket) throws IOException {

        System.out.println("Waiting for connection");
        clientSocket = serverSocket.accept();
        System.out.println("Client accepted: " + clientSocket);
    }



    private void cleanup(Closeable closeable) {

        if (closeable != null) {
            try {
                if (clientSocket.isClosed() || serverSocket.isClosed())
                    closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
