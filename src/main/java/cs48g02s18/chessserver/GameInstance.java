package cs48g02s18.chessserver;

import cs48g02s18.chessgame.*;

import java.util.Timer;
import java.util.TimerTask;

public class GameInstance {
   private ServerPlayer hostServerPlayer; //future change to more generalized form (doesn't need to be host/guest
    private ServerPlayer guestServerPlayer;             //eg if we wanted to have a game queue
    private GameHost gameHost;
    private int turnNumber;
    private Timer timer;
    long turnLength;


    public GameInstance(ServerPlayer hostServerPlayer, Timer timer) {
        this.turnNumber = 0;
        this.timer = timer;
        turnLength = 15000; //15s turns
        this.hostServerPlayer = hostServerPlayer;
        this.guestServerPlayer = hostServerPlayer;
        this.setUpBoard();
    }

    public void addPlayer(ServerPlayer guestServerPlayer){
        if (this.guestServerPlayer == this.hostServerPlayer){
            this.guestServerPlayer = guestServerPlayer;
            this.setUpBoard();
        }
    //todo
    }

    private void setUpBoard(){
        this.gameHost = new GameHost();
        this.guestServerPlayer.setGamePlayer(gameHost.blackPlayer);
        this.hostServerPlayer.setGamePlayer(gameHost.whitePlayer);
    }

    public void resolveTurn() {
        if (gameHost.checkIfReady()){ //if it did a turn, it will return 1
            this.turnNumber++;
            System.out.print("turn: " + this.turnNumber);
        }
        else {
            if (gameHost.blackPlayer.getNextMove() != null) {
                gameHost.blackPlayer.getNextMove().setSubmittedFirst(true);
            }
            else if (gameHost.whitePlayer.getNextMove() != null) {
                gameHost.whitePlayer.getNextMove().setSubmittedFirst(true);
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
            System.out.print("timer scheduled");
        }
    }

    public void timeUp(int turnNumber){
        System.out.print("timeUp called " + this.turnNumber);
        if (turnNumber == this.turnNumber){
            this.turnNumber++;
            this.gameHost.executeGameTurn();
            System.out.print("timeUp caused execution");
        }

    }

    public Board getGameBoard() {
        if (gameHost != null)
            return gameHost.gameBoard;
        else return null;
    }
}
