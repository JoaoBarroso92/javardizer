package org.academiadecodigo.asynctomatics56.javardizer;


import org.academiadecodigo.asynctomatics56.javardizer.utils.Ascii;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Questions;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;


public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader inputReader;
    public static Map<Socket, Integer> scoreBoard;
    public static Map<Socket, String> connectedUsers;
    private Prompt prompt;
    private MakingQuestions makingQuestions;


    public ClientHandler(Socket clientSocket, Map<Socket, String> connectedUsers) {
        this.clientSocket = clientSocket;
        this.connectedUsers = connectedUsers;
        this.scoreBoard = new HashMap<>();

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
            } else if(inputReader.readLine().equals("/r")) {
                try {
                    startGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (inputReader.readLine().equals("/shutdown")) {
                System.out.println("HERE");
                os.write("System Exit".getBytes());
                System.exit(0);
            }


        }
    }

    private void startGame() throws IOException, InterruptedException {

        InputStream in = clientSocket.getInputStream();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        prompt = new Prompt(in, out);
        makingQuestions = new MakingQuestions();

        out.write(Ascii.Q1.getBytes());
        makingQuestions.question1(Questions.Q1, 1, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q2.getBytes());
        makingQuestions.question2(Questions.Q2, 4, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q3.getBytes());
        makingQuestions.question3(Questions.Q3, 3, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q4.getBytes());
        makingQuestions.question4(Questions.Q4, 2, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q5.getBytes());
        makingQuestions.question5(Questions.Q5, 2, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.SUPER_BOCK.getBytes());
        Thread.sleep(20000);
        out.write(Ascii.Q6.getBytes());
        makingQuestions.question6(Questions.Q6, 1, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q7.getBytes());
        makingQuestions.question7(Questions.Q7, 4, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q8.getBytes());
        makingQuestions.question8(Questions.Q8, 3, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q9.getBytes());
        makingQuestions.question9(Questions.Q9, 1, prompt, clientSocket, scoreBoard, connectedUsers);
        out.write(Ascii.Q10.getBytes());
        makingQuestions.question10(Questions.Q10, 2, prompt, clientSocket, scoreBoard, connectedUsers);
    }
    
    private String createUsername() throws IOException {
        InputStream in = clientSocket.getInputStream();
        PrintStream out = new PrintStream(clientSocket.getOutputStream());
        Prompt prompt = new Prompt(in, out);
        StringInputScanner scnfake = new StringInputScanner();
        scnfake.setMessage("");
        if(prompt.getUserInput(scnfake).equals("/s")){
            StringInputScanner scn = new StringInputScanner();
            scn.setMessage(Messages.ENTER_USERNAME);
            return prompt.getUserInput(scn);
        }
        return null;
    }

    private void addClient() throws IOException {
        String username = createUsername();
        if (!connectedUsers.containsValue(username)) {
            connectedUsers.put(clientSocket, username);
            scoreBoard.put(clientSocket, 0);
            try {
                startGame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
