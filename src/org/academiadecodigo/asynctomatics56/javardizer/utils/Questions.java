package org.academiadecodigo.asynctomatics56.javardizer.utils;

import org.academiadecodigo.asynctomatics56.javardizer.Game;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Questions {

    public static final String Q1 = "What's the first letter of the alphabet?";
    public static final String Q1_ANS_A = "A";
    public static final String Q1_ANS_B = "B";
    public static final String Q1_ANS_C = "C";
    public static final String Q1_ANS_D = "D";

    public static final String Q2 = "What's the first letter of the alphabet?";
    public static final String Q2_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q2_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q2_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q2_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q3 = "What's the first letter of the alphabet?";
    public static final String Q3_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q3_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q3_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q3_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q4 = "What's the first letter of the alphabet?";
    public static final String Q4_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q4_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q4_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q4_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q5 = "What's the first letter of the alphabet?";
    public static final String Q5_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q5_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q5_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q5_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q6 = "What's the first letter of the alphabet?";
    public static final String Q6_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q6_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q6_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q6_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q7 = "What's the first letter of the alphabet?";
    public static final String Q7_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q7_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q7_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q7_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q8 = "What's the first letter of the alphabet?";
    public static final String Q8_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q8_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q8_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q8_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q9 = "What's the first letter of the alphabet?";
    public static final String Q9_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q9_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q9_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q9_ANS_D = "What's the first letter of the alphabet?";

    public static final String Q10 = "What's the first letter of the alphabet?";
    public static final String Q10_ANS_A = "What's the first letter of the alphabet?";
    public static final String Q10_ANS_B = "What's the first letter of the alphabet?";
    public static final String Q10_ANS_C = "What's the first letter of the alphabet?";
    public static final String Q10_ANS_D = "What's the first letter of the alphabet?";

    public static int counter = 0;

    private static void timer() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void question1(
            String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q1_ANS_A,Questions.Q1_ANS_B , Questions.Q1_ANS_C, Questions.Q1_ANS_D};
        MenuInputScanner question1 = new MenuInputScanner(options);
        question1.setMessage(question);


        int answerIndex = prompt.getUserInput(question1);
        timer();

        System.out.println("Timer");


        int score = (Integer) scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:

                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write(("Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write(("Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

        question2(question, correctAnswer, prompt, clientSocket, scoreBoard, connectedUsers);
    }

    public static void question2(
            String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {"e", "f", "g", "h"};
        MenuInputScanner question2 = new MenuInputScanner(options);
        question2.setMessage(question);


        int answerIndex = prompt.getUserInput(question2);
        timer();


        System.out.println("Timer");


        int score = (Integer) scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write(("Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write(("Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
        }
        counter++;
        endGame(scoreBoard, connectedUsers);
    }

    public static void question3
            (String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {"e", "f", "g", "h"};
        MenuInputScanner question3 = new MenuInputScanner(options);
        question3.setMessage(question);

        int answerIndex = prompt.getUserInput(question3);
        timer();

        System.out.println("Timer");

        int score = (Integer) scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write(("Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write(("Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
        }
    }

    public static void endGame(Map<Socket, Integer> scoreBoard, Map<Socket, Integer> connectedUsers) throws IOException {

        if(counter == Game.MAX_PLAYERS){
            for(Socket socket : scoreBoard.keySet()){
                for(Socket sockets : connectedUsers.keySet()){
                    OutputStream right = socket.getOutputStream();
                    right.write((connectedUsers.get(sockets) + " : " + scoreBoard.get(sockets) + " points").getBytes());
                    System.out.println(((connectedUsers.get(sockets) + " : " + scoreBoard.get(sockets) + " points")));
                }
            }
            for(Socket socket : scoreBoard.keySet()){
                OutputStream wrong = socket.getOutputStream();
                wrong.write(chooseWinner(scoreBoard, connectedUsers).getBytes());
            }

        }
    }

    public static String chooseWinner(Map<Socket, Integer> scoreBoard, Map connectedUsers) {

        Integer score = 0;
        Socket winner = null;

        for (Socket jb : scoreBoard.keySet()) {
            if (scoreBoard.get(jb) > score) {
                score = scoreBoard.get(jb);
                winner = jb;
            }
        }
        return connectedUsers.get(winner) + " " + score;
    }
}



