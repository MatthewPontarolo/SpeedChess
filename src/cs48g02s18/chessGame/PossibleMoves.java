package cs48g02s18.chessServer.cs48g02s18.chessGame;

import java.awt.Point;
import java.util.ArrayList;

public class PossibleMoves
{
  // white = 0
  // black = 1
  int playerType;
  public PossibleMoves(int playerType)
  {
    this.playerType = playerType;
  }

  // for UI, call getPossibleMoves(Piece selectedPiece, gameBoard, int playerType)
  // whitePlayer -- playerType = 0, blackPlayer -- playerType = 1
  // returns arrayList of Points for valid moves of current piece position
    // NOTE: will possibly reconsider use of Points bc of return type double
  // deal with offsets depending on which player perspective
  // NOTE: only whitePlayer works right now, bc of possible board flipping(??)
    // if board flipping won't happen, I can add in proper offsets for blackPlayer as well.
  public static ArrayList<Point> getPossibleMoves(Piece p, Board board, int playerType)
  {
    ArrayList<Point> moves = new ArrayList<Point>();
    ArrayList<Point> offsets = p.getValidMoves(board, playerType);
    // white player -- adds offset
    if (playerType == 0)
    {
      for (Point k : offsets)
      {
        // piece(X) + offset(X)
        int x = p.getXPosition() + (int)k.getX();
        int y = p.getYPosition() + (int)k.getY();
        Point move = new Point(x, y);
        if (x >=0 && x < 8 && y >= 0 && y < 8 && board.getPiece(x, y) == null)
        {
          moves.add(move);
          System.out.println(x + ", " + y);
        }
      }
    }
    // black player -- minus offset
    else if (playerType == 1)
    {
      /*
      for (Point k : offsets)
      {
        // piece(X) - offset(X)
        int x = p.getXPosition() + (int)k.getX();
        int y = p.getYPosition() - (int)k.getY();
        System.out.println(x + ", " + y);

        Point move = new Point(x, y);
        if (x >=0 && x < 8 && y >= 0 && y < 8 && board.getPiece(x, y) == null)
        {
          moves.add(move);
        }
      }*/
    }
    return moves;

  }


}
