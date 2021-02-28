package org.academiadecodigo.asynctomatics56.javardizer;


import org.academiadecodigo.asynctomatics56.javardizer.utils.Ascii;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;


import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
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
        new Game(connectedUsers);

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

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        clientHandler = new ClientHandler(clientSocket, connectedUsers);
        executor.submit(clientHandler);
    }

    private void listen(ServerSocket serverSocket) throws IOException {

        clientSocket = serverSocket.accept();
        OutputStream os = clientSocket.getOutputStream();
        os.write(Ascii.MAIN_MENU_IMG.getBytes());
        os.write(Messages.START_GAME.getBytes());
        os.write(Messages.QUIT_GAME.getBytes());
        os.flush();
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
