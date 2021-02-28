package org.academiadecodigo.asynctomatics56.javardizer;


import org.academiadecodigo.bootcamp.Prompt;


import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Game {

    //CHANGE THE PROGRAM TO COUNT THE PLAYER USING THE SOCKET LIST SIZE

    private Prompt prompt;
    public static final int MAX_PLAYERS = 2;
    private Map<String, Integer> scoreBoard;
    private Map<Socket, String> connectedUsers;

    public Game(Map<Socket, String> connectedUsers) {

        this.connectedUsers = connectedUsers;
        this.scoreBoard = new HashMap<>();

        waitingForPlayers();
    }


    public void waitingForPlayers() {

        if (connectedUsers.size() < MAX_PLAYERS) {
            //wait
            System.out.println("playerList size: " + connectedUsers.size());
        }
        if (connectedUsers.size() == MAX_PLAYERS) {
        }
    }

}
