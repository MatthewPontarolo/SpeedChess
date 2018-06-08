package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Move;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassMoveData extends DataPass {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    @JsonIgnore
    public DataPassMoveData(String username, String password, MoveData move) {
        super(username, password);
        this.fromX = move.getFromX();
        this.fromY = move.getFromY();
        this.toX = move.getToX();
        this.toY = move.getToY();
    }

    @JsonIgnore
    public DataPassMoveData(String username, String password, Move gameMove) {
        super(username, password);
        this.fromX = gameMove.getInitX();
        this.fromY = gameMove.getInitY();
        this.toX = gameMove.getXMove();
        this.toY = gameMove.getYMove();
    }

    public DataPassMoveData() {
        super();
        this.fromX = -1;
        this.fromY = -1;
        this.toX = -1;
        this.toY = -1;
    }

    @JsonIgnore
    public MoveData getMove() {
        return new MoveData(fromX, fromY, toX, toY);
    }

    @JsonIgnore
    public void setMove(MoveData move) {
        this.fromX = move.getFromX();
        this.fromY = move.getFromY();
        this.toX = move.getToX();
        this.toY = move.getToY();
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    @Override
    public String toString() {
        return "DataPassMoveData{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                '}';
    }
}
