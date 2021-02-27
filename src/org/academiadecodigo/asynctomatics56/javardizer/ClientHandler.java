package org.academiadecodigo.asynctomatics56.javardizer;


import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Questions;
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
    private Prompt prompt;


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
        System.out.println("playerList size: " + connectedUsers.size());
        while (!clientSocket.isClosed()) {
            broadcast();
        }
    }

    private void broadcast() throws IOException {
        synchronized (this) {
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream os = clientSocket.getOutputStream();
            if (inputReader.readLine().equals("/quit")) {
                os.write(Messages.EXIT_GAME_MSG.getBytes());
                os.flush();
                os.close();
                this.clientSocket.close();
            } else {
                if ((connectedUsers.size() < Game.MAX_PLAYERS)) {
                    os.write(Messages.NEED_MORE_PLAYERS.getBytes());
                } else {
                    try {
                        startGame();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void startGame() throws IOException, InterruptedException {

        InputStream in = clientSocket.getInputStream();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        prompt = new Prompt(in, out);

        Questions.question1(Questions.QUESTION_1, 1, prompt, clientSocket);

    }
    
    private String createUsername() throws IOException {
        InputStream in = clientSocket.getInputStream();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        Prompt prompt = new Prompt(in, out);
        StringInputScanner scn = new StringInputScanner();
        scn.setMessage(Messages.ENTER_USERNAME);
        return prompt.getUserInput(scn);
    }

    private void addClient() throws IOException {
        String username = createUsername();
        if (!connectedUsers.containsValue(username)) {
            connectedUsers.put(clientSocket, username);
        } else {
            OutputStream os = clientSocket.getOutputStream();
            os.write(Messages.NAME_ALREADY_IN_USE.getBytes());
            os.flush();
            addClient();
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
