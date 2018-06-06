package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessserver.MoveData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassBoardState {
    String boardData;
    MoveData opponentLastMove;


    public DataPassBoardState() {
        this.boardData = null;
    }

    public DataPassBoardState(String boardData) {
        this.boardData = boardData;
        opponentLastMove = null;
    }

    public String getBoardData() {
        return boardData;
    }

    public MoveData getOpponentLastMove() {
        return opponentLastMove;
    }


    public void setBoardData(String boardData) {
        this.boardData = boardData;
    }

    public void setOpponentLastMove(MoveData opponentLastMove) {
        this.opponentLastMove = opponentLastMove;
    }


}
