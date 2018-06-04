
public class Move {
  private int xPosition;
  private int yPosition;
  private Piece target;
  private long time;

  public Move(Piece p, int x, int y, double time) {
    xPosition = x;
    yPosition = y;
    target = p;
    this.time = time;
  }


  public Move(String m) {

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
	public long getTime() {
		return time;
	}

  public void setTime(long timestamp)
  {
    time = timestamp;
  }

	public String packageToString() {
		return target.getXPosition() + " "
				+ target.getYPosition() + " "
				+ xPosition + " "
				+ yPosition + " "
				+ time;
	}
}
