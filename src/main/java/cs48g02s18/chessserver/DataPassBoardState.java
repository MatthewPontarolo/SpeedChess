package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Move;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassBoardState {
    String boardData;
    Move opponentLastMove;
    int playerNumber;
    int timeLeft;


    public DataPassBoardState() {
        this.boardData = null;
        opponentLastMove = null;
        playerNumber = -1;
        this.timeLeft = 0;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public DataPassBoardState(String boardData) {
        this.boardData = boardData;
        this.opponentLastMove = null;
    }

    public DataPassBoardState(String boardData, int playerNumber) {
        this.boardData = boardData;
        this.opponentLastMove = null;
        this.playerNumber = playerNumber;
    }

    public DataPassBoardState(String boardData, int playerNumber, int timeLeft) {
        this.boardData = boardData;
        this.opponentLastMove = null;
        this.playerNumber = playerNumber;
        this.timeLeft = timeLeft;
    }

    public String getBoardData() {
        return boardData;
    }

    public Move getOpponentLastMove() {
        return opponentLastMove;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setBoardData(String boardData) {
        this.boardData = boardData;
    }

    public void setOpponentLastMove(Move opponentLastMove) {
        this.opponentLastMove = opponentLastMove;
    }

    @Override
    public String toString() {
        return "DataPassBoardState{" +
                "boardData='" + boardData + '\'' +
                ", opponentLastMove=" + opponentLastMove +
                ", playerNumber=" + playerNumber +
                '}';
    }
}
