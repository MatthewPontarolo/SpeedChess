package cs48g02s18.chessServer;

import cs48g02s18.chessGame.Piece;

import java.awt.Point;

public class Move {
    private Point startPosition;
    private Point endPosition;

    public Move(Point startPosition, Point endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Move(Piece pieceToMove, Point endPosition) {
        this.startPosition = new Point(pieceToMove.getXPosition(), pieceToMove.getYPosition());
        this.endPosition = endPosition;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public Point getEndPosition() {
        return endPosition;
    }
}
