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

    public MoveData(Point startPosition, Point endPosition) {
        this.fromX = startPosition.x;
        this.fromY = startPosition.y;
        this.toX = endPosition.x;
        this.toY = endPosition.y;
    }

    public MoveData(int a, int b, int c, int d){
        this.fromX = a;
        this.fromY = b;
        this.toX = c;
        this.toY = d;
    }

    public MoveData(Piece pieceToMove, Point endPosition) {
        this.toX = pieceToMove.getXPosition();

        this.toY = pieceToMove.getYPosition());
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
        this.fromX = endPosition.x;
        this.fromY = endPosition.y;
    }
}
