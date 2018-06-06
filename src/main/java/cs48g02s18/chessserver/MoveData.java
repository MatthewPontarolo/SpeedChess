package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Piece;

import java.awt.Point;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveData {
    private Point startPosition;
    private Point endPosition;

    public MoveData(Point startPosition, Point endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public MoveData(int a, int b, int c, int d){
        this.startPosition = new Point(a,b);
        this.endPosition = new Point(c,d);
    }

    public MoveData(Piece pieceToMove, Point endPosition) {
        this.startPosition = new Point(pieceToMove.getXPosition(), pieceToMove.getYPosition());
        this.endPosition = endPosition;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public Point getEndPosition() {
        return endPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }
}
