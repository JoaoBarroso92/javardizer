package org.academiadecodigo.asynctomatics56.concurrentchatserver;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;


public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    private List<Socket> connectedUsers;


    public ClientHandler(Socket clientSocket, List<Socket> connectedUsers) {
        this.clientSocket = clientSocket;
        this.connectedUsers = connectedUsers;
    }

    @Override
    public void run() {

        try {
            serve();
        } catch (SocketException e) {
            cleanup(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cleanup(inputReader);
        }
    }

    private void serve() throws IOException {

        while (!clientSocket.isClosed()) {
            readAndSend();
        }
    }

    private void readAndSend() throws IOException {

        inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = inputReader.readLine() + "\n";
        System.out.println("Sending Message to User");
        sendMessage(message);
    }

    private void sendMessage(String message) throws IOException {

        for (Socket s : connectedUsers) {
            if (s != null && !s.equals(this.clientSocket)) {
                OutputStream os = s.getOutputStream();
                os.write(message.getBytes());
                os.flush();
            }
        }
    }


    private void cleanup(Closeable closeable) {

        if (closeable != null) {
            try {
                closeable.close();
                for (Socket s : connectedUsers) {
                    if (s.equals(this.clientSocket) && s.isClosed()) {
                        connectedUsers.remove(s);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
