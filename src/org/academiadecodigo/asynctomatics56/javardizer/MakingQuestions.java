package org.academiadecodigo.asynctomatics56.javardizer;

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

    public int counter = 0;

    private void timer() {
        try {
            Thread.sleep(10000);
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

        question2(question, correctAnswer, prompt, clientSocket, scoreBoard, connectedUsers);
    }

    public void question2(
            String question, int correctAnswer, Prompt prompt, Socket clientSocket, Map scoreBoard, Map connectedUsers)
            throws IOException, InterruptedException {

        String[] options = {"e", "f", "g", "h"};
        MenuInputScanner question2 = new MenuInputScanner(options);
        question2.setMessage(question);

        int answerIndex = prompt.getUserInput(question2);
        timer();

        int score = (Integer) scoreBoard.get(clientSocket);
        switch (answerIndex) {
            case 1:
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
