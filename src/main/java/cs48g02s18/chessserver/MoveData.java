package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Piece;

import java.awt.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveData {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;

    public MoveData() {
        this.fromX = -1;
        this.fromY = -1;
        this.toX = -1;
        this.toY = -1;
    }

    public MoveData(int a, int b, int c, int d){
        this.fromX = a;
        this.fromY = b;
        this.toX = c;
        this.toY = d;
    }

    public MoveData(Piece pieceToMove, Point endPosition) {
        this.toX = pieceToMove.getXPosition();
        this.toY = pieceToMove.getYPosition();
        this.toX = endPosition.x;
        this.toY = endPosition.y;
    }




    public Point getStartPosition() {
        return new Point(fromX, fromY);
    }

    public Point getEndPosition() {
        return new Point(toX, toY);
    }

    public void setStartPosition(Point startPosition) {
        this.fromX = startPosition.x;
        this.fromY = startPosition.y;
    }

    public void setEndPosition(Point endPosition) {
        this.toX = endPosition.x;
        this.toY = endPosition.y;
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

    @Override
    public String toString() {
        return "MoveData{" +
                "fromX=" + fromX +
                ", fromY=" + fromY +
                ", toX=" + toX +
                ", toY=" + toY +
                '}';
    }
}