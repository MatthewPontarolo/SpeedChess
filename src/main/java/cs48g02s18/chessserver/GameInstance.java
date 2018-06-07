package cs48g02s18.chessserver;

import cs48g02s18.chessgame.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameInstance {
   private ServerPlayer hostServerPlayer; //future change to more generalized form (doesn't need to be host/guest
    private ServerPlayer guestServerPlayer;             //eg if we wanted to have a game queue
    private GameHost gameHost;
    private Player whitePlayer;
    private Player blackPlayer;
    private int turnNumber;
    private Timer timer;
    long turnLength;


    public GameInstance(ServerPlayer hostServerPlayer, Timer timer) {
        this.hostServerPlayer = hostServerPlayer;
        this.turnNumber = 0;
        this.timer = timer;
        turnLength = 15000; //15s turns
        this.setUpBoard();
    }

    public void addPlayer(ServerPlayer guestServerPlayer){
        if (this.guestServerPlayer == null){
            this.guestServerPlayer = guestServerPlayer;
            this.setUpBoard();
        }
    //todo
    }

    private void setUpBoard(){
        this.gameHost = new GameHost();
        this.whitePlayer = this.gameHost.whitePlayer;
        this.blackPlayer = this.gameHost.blackPlayer;
    }

    public void resolveTurn() {
        if (gameHost.checkIfReady()){ //if it did a turn, it will return 1
            this.turnNumber++;
        }
        else {
            if (blackPlayer.getNextMove() != null) {
                blackPlayer.getNextMove().setSubmittedFirst(true);
            }
            else if (whitePlayer.getNextMove() != null) {
                whitePlayer.getNextMove().setSubmittedFirst(true);
            }

            GameInstance g = this;
            int turn = turnNumber;
            TimerTask task = new TimerTask() {
                GameInstance game = g;
                int turnNumber = turn;
                @Override
                public void run() {
                    game.timeUp(turn);
                }
            };

            timer.schedule(task, this.turnLength);
        }
    }

    public void timeUp(int turnNumber){
        if (turnNumber == this.turnNumber){
            this.turnNumber++;
            this.gameHost.executeGameTurn();
        }

    }

    public Board getGameBoard() {
        return gameHost.gameBoard;
    }
}
