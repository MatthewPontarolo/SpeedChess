package cs48g02s18.chessgame;

public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;

  public Move(Piece p, int x, int y)
  {
    xPosition = x;
    yPosition = y;
    target = p;
  }

  public Piece getTargetPiece()
  {
    return target;
  }

  public int getXMove()
  {
    return xPosition;
  }

  public int getYMove()
  {
    return yPosition;
  }

  public int getInitX()
  {
    return target.getXPosition();
  }

  public int getInitY()
  {
    return target.getYPosition();
  }
}
