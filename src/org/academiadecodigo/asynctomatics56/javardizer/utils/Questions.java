package org.academiadecodigo.asynctomatics56.javardizer.utils;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Questions {

    public static final String QUESTION_1 = "What's the first letter of the alphabet?";


    private static void timer() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void question1(String question, int correctAnswer, Prompt prompt, Socket clientSocket) throws IOException, InterruptedException {
        String[] options = {"a", "b", "c", "d"};
        MenuInputScanner question1 = new MenuInputScanner(options);
        question1.setMessage(question);

        int answerIndex = prompt.getUserInput(question1);

        timer();
        System.out.println("Timer");

        switch (answerIndex) {
            case 1:
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write("Correct answer".getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write("Wrong answer".getBytes());
        }
    }
}


