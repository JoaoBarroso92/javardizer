package org.academiadecodigo.asynctomatics56.javardizer;


import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;


public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    private Map<Socket, String> connectedUsers;


    public ClientHandler(Socket clientSocket, Map<Socket, String> connectedUsers) {
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

        addClient();
        while (!clientSocket.isClosed()) {
            readAndSend();
        }
    }

    private void readAndSend() throws IOException {

        inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        if(inputReader.readLine().equals("/quit")) {
            OutputStream os = clientSocket.getOutputStream();
            String s = "ja fostes\n";
            os.write(s.getBytes());
            os.flush();
            os.close();
            this.clientSocket.close();
        } else {
            String message = inputReader.readLine() + "\n";
            sendMessage(message);
        }

    }

    private void sendMessage(String message) throws IOException {

        for (Socket s : connectedUsers.keySet()) {
            if (s != null && !s.equals(this.clientSocket)) {
                OutputStream os = s.getOutputStream();
                String username = connectedUsers.get(this.clientSocket) + ": " + message;
                os.write(username.getBytes());
                os.flush();
                os.close();
            }
        }
    }

    private String createUsername() throws IOException {

        InputStream in = clientSocket.getInputStream();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        Prompt prompt = new Prompt(in, out);
        StringInputScanner scn = new StringInputScanner();
        scn.setMessage("Enter a username: ");
        return prompt.getUserInput(scn);


    }

    private void addClient() throws IOException {

        String username = createUsername();

        if (!connectedUsers.containsValue(username)) {
            connectedUsers.put(clientSocket, username);
        } else {
            String s = "ERROR: " + username + " already in use!";
            OutputStream os = clientSocket.getOutputStream();
            os.write(s.getBytes());
            os.flush();
            os.close();
        }
    }

    private void cleanup(Closeable closeable) {

        if (closeable != null) {
            try {
                closeable.close();
                for (Socket s : connectedUsers.keySet()) {
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
