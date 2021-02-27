package org.academiadecodigo.asynctomatics56.javardizer.utils;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class Questions {

    public static final String QUESTION_1 = "What's the first letter of the alphabet?";

    private static void timer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void question1(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard) throws IOException, InterruptedException {
        String[] options = {"a", "b", "c", "d"};
        MenuInputScanner question1 = new MenuInputScanner(options);
        question1.setMessage(question);


        int answerIndex = prompt.getUserInput(question1);
        timer();

        System.out.println("Timer");


        int score = (Integer)scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:

                scoreBoard.replace(clientSocket, score, score+10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write(("Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write(("Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

        question2(question, correctAnswer, prompt, clientSocket, scoreBoard);
    }

    public static void question2(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard) throws IOException, InterruptedException {
        String[] options = {"e", "f", "g", "h"};
        MenuInputScanner question2 = new MenuInputScanner(options);
        question2.setMessage(question);


        int answerIndex = prompt.getUserInput(question2);
        timer();


        System.out.println("Timer");


        int score = (Integer)scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:

                scoreBoard.replace(clientSocket, score, score+10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write(("Correct answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write(("Wrong answer. Your score is: " + scoreBoard.get(clientSocket) + "\n").getBytes());
        }
    }
}




