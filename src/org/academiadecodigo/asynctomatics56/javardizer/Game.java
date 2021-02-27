package org.academiadecodigo.asynctomatics56.javardizer;

import org.academiadecodigo.asynctomatics56.javardizer.utils.Messages;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game {

    //CHANGE THE PROGRAM TO COUNT THE PLAYER USING THE SOCKET LIST SIZE

    private Prompt prompt;
    private final int MAX_PLAYERS = 5;
    private Map<String, Integer> scoreBoard;
    private Map<Socket, String> connectedUsers;

    public Game(Map<Socket, String> connectedUsers) {

        this.connectedUsers = connectedUsers;
        this.scoreBoard = new HashMap<>();

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

    }
}
