package org.academiadecodigo.asynctomatics56.javardizer;

import org.academiadecodigo.asynctomatics56.javardizer.utils.Ascii;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Colors;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;
import org.academiadecodigo.asynctomatics56.javardizer.utils.Questions;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class MakingQuestions {

    public static int counter = 0;

    private void timer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void question1(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q1_ANS_A,Questions.Q1_ANS_B , Questions.Q1_ANS_C, Questions.Q1_ANS_D};

        MenuInputScanner question1 = new MenuInputScanner(options);
        question1.setMessage(question);

        int answerIndex = prompt.getUserInput(question1);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 1:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

        //question2(question, correctAnswer, prompt, clientSocket, scoreBoard, connectedUsers);
    }

    public void question2(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q2_ANS_A,Questions.Q2_ANS_B, Questions.Q2_ANS_C, Questions.Q2_ANS_D};

        MenuInputScanner question2 = new MenuInputScanner(options);
        question2.setMessage(question);

        int answerIndex = prompt.getUserInput(question2);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 4:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }

    public void question3(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q3_ANS_A,Questions.Q3_ANS_B, Questions.Q3_ANS_C, Questions.Q3_ANS_D};

        MenuInputScanner question3 = new MenuInputScanner(options);
        question3.setMessage(question);

        int answerIndex = prompt.getUserInput(question3);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 3:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }


    public void question4(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q4_ANS_A,Questions.Q4_ANS_B, Questions.Q4_ANS_C, Questions.Q4_ANS_D};

        MenuInputScanner question4 = new MenuInputScanner(options);
        question4.setMessage(question);

        int answerIndex = prompt.getUserInput(question4);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 2:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }

    public void question5(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q5_ANS_A,Questions.Q5_ANS_B, Questions.Q5_ANS_C, Questions.Q5_ANS_D};

        MenuInputScanner question5 = new MenuInputScanner(options);
        question5.setMessage(question);

        int answerIndex = prompt.getUserInput(question5);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 2:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }

    public void question6(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q6_ANS_A,Questions.Q6_ANS_B, Questions.Q6_ANS_C, Questions.Q6_ANS_D};

        MenuInputScanner question6 = new MenuInputScanner(options);
        question6.setMessage(question);

        int answerIndex = prompt.getUserInput(question6);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 1:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }


    public void question7(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q7_ANS_A,Questions.Q7_ANS_B, Questions.Q7_ANS_C, Questions.Q7_ANS_D};

        MenuInputScanner question7 = new MenuInputScanner(options);
        question7.setMessage(question);

        int answerIndex = prompt.getUserInput(question7);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 4:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }

    public void question8(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q8_ANS_A,Questions.Q8_ANS_B, Questions.Q8_ANS_C, Questions.Q8_ANS_D};

        MenuInputScanner question8 = new MenuInputScanner(options);
        question8.setMessage(question);

        int answerIndex = prompt.getUserInput(question8);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 3:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }


    public void question9(String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q9_ANS_A,Questions.Q9_ANS_B, Questions.Q9_ANS_C, Questions.Q9_ANS_D};

        MenuInputScanner question9 = new MenuInputScanner(options);
        question9.setMessage(question);

        int answerIndex = prompt.getUserInput(question9);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);

        switch (answerIndex) {
            case 1:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n").getBytes());
        }

    }



    public void question10(
            String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {Questions.Q10_ANS_A,Questions.Q10_ANS_B , Questions.Q10_ANS_C, Questions.Q10_ANS_D};
        MenuInputScanner question10 = new MenuInputScanner(options);
        question10.setMessage(question);

        int answerIndex = prompt.getUserInput(question10);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 2:
                scoreBoard.replace(clientSocket, score, score + 10);
                OutputStream option1 = clientSocket.getOutputStream();
                option1.write((Messages.CORRECT_ANSWER + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
                break;
            default:
                OutputStream wrong = clientSocket.getOutputStream();
                wrong.write((Messages.WRONG_ANSWER + scoreBoard.get(clientSocket) + "\n" + Colors.RESET).getBytes());
        }
        counter++;
        endGame(scoreBoard, connectedUsers);
    }



    public void endGame(Map<Socket, Integer> scoreBoard, Map<Socket, Integer> connectedUsers) throws IOException {

        if(counter == Game.MAX_PLAYERS){
            for(Socket socket : connectedUsers.keySet()){
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

    public String chooseWinner(Map<Socket, Integer> scoreBoard, Map connectedUsers) {

        Integer score = 0;
        Socket winner = null;

        for (Socket jb : scoreBoard.keySet()) {
            if (scoreBoard.get(jb) > score) {
                score = scoreBoard.get(jb);
                winner = jb;
            }
        }
        return (Colors.BLUE_BOLD_BRIGHT + connectedUsers.get(winner) + " wins the game with " + score + " points " + Colors.RESET + "\n");
    }

}
