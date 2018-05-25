public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;

  public void Move(Piece p, int x, int y)
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
}
