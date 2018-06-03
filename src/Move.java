public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;
  private double time;

  public Move(Piece p, int x, int y, double time)
  {
    xPosition = x;
    yPosition = y;
    target = p;
  }


  public Move(String m)
  {

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

	// Server's timer timestamp
	public double getTime() {
		return time;
	}

	public String packageToString() {
		return target.getXPosition() + " "
				+ target.getYPosition() + " "
				+ xPosition + " "
				+ yPosition + " "
				+ time;
	}
}
