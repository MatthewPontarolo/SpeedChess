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

  // deal with offsets depending on which player perspective
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
        if (board.getPiece(x, y) == null)
        {
          moves.add(move);
        }
      }
    }
    // black player -- minus offset
    else if (playerType == 1)
    {
      for (Point k : offsets)
      {
        // piece(X) - offset(X)
        int x = p.getXPosition() - (int)k.getX();
        int y = p.getYPosition() - (int)k.getY();
        Point move = new Point(x, y);
        if (board.getPiece(x, y) == null)
        {
          moves.add(move);
        }
      }
    }
    return moves;

  }


}
