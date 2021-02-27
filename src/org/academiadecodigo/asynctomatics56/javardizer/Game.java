package org.academiadecodigo.asynctomatics56.javardizer;

import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.net.Socket;
import java.util.List;


public class Game {

    //CHANGE THE PROGRAM TO COUNT THE PLAYER USING THE SOCKET LIST SIZE

    private Prompt prompt;
    private final int MAX_PLAYERS = 5;
    private int scoreBoard;
    private List<Socket> connectedUsers;

    public Game(List<Socket> connectedUsers) {

        prompt = new Prompt(System.in, System.out);
        this.connectedUsers = connectedUsers;

        waitingForPlayers();
        //gameLogic();
    }


    public void waitingForPlayers() {

        if (connectedUsers.size() < MAX_PLAYERS) {
            //wait
            System.out.println("playerList size: " + connectedUsers.size());
        }
        if (connectedUsers.size() == MAX_PLAYERS) {

        }
    }


    // The "prompt view" messages should be sent via PrintWriter!
    public void gameLogic() {

        String[] options = {"Start game", "Exit game"};

        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Should I stay or should I go?");

        int answerIndex = prompt.getUserInput(scanner);

        System.out.println("User wants to " + options[answerIndex - 1]);

        switch (answerIndex) {
            case 1:
                System.out.println("Option 1");
                break;
            case 2:
                System.out.println("Option 2");
                break;
            default:
                break;
        }
    }
}
