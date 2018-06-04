package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Move;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassBoardState {
    String boardData;
    Move opponentLastMove;


    public DataPassBoardState() {
        this.boardData = null;
    }

    public DataPassBoardState(String boardData) {
        this.boardData = boardData;
    }

    public String getBoardData() {
        return boardData;
    }

    public Move getOpponentLastMove() {
        return opponentLastMove;
    }

    public void setBoardData(String boardData) {
        this.boardData = boardData;
    }

    public void setOpponentLastMove(Move opponentLastMove) {
        this.opponentLastMove = opponentLastMove;
    }


}
