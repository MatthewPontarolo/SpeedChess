public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;
  private double time;

  public void Move(Piece p, int x, int y, double time)
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

  // Server's timer timestamp
  public double getTime()
  {
    return time;
  }
}
