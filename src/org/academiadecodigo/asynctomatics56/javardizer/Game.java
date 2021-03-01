package org.academiadecodigo.asynctomatics56.javardizer;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Game {

    //CHANGE THE PROGRAM TO COUNT THE PLAYER USING THE SOCKET LIST SIZE
    public static final int MAX_PLAYERS = 6;
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

    }

}
