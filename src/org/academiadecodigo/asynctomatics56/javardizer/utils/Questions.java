package org.academiadecodigo.asynctomatics56.javardizer.utils;

import org.academiadecodigo.asynctomatics56.javardizer.Game;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class Questions {

    public static final String QUESTION_1 = "What's the first letter of the alphabet?";
    public static final String QUESTION_2 = "What's the sixth letter of the alphabet?";
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

        String[] options = {"a", "b", "c", "d"};
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
                option1.write((Colors.GREEN + "Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Colors.RED + "Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
        }

        question2(QUESTION_2, 2, prompt, clientSocket, scoreBoard, connectedUsers);
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
            case 2:

                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Colors.GREEN + "Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Colors.RED + "Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
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
                option1.write((Colors.GREEN + "Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Colors.RED + "Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
        }
    }

    public static void endGame(Map<Socket, Integer> scoreBoard, Map<Socket, Integer> connectedUsers) throws IOException {

        if(counter == Game.MAX_PLAYERS){
            for(Socket socket : scoreBoard.keySet()){
                for(Socket sockets : connectedUsers.keySet()){
                    OutputStream right = socket.getOutputStream();
                    right.write((Colors.PURPLE + connectedUsers.get(sockets) + " finished with " + scoreBoard.get(sockets) + " points" + Colors.RESET + "\n").getBytes());
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
        return (Colors.BLUE_BOLD_BRIGHT + connectedUsers.get(winner) + " wins with " + score + " points " + Colors.RESET + "\n");
    }
}



