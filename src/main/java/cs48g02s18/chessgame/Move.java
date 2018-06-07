package cs48g02s18.chessgame;

public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;
  private boolean submittedFirst;

  public Move(Piece p, int x, int y)
  {
    xPosition = x;
    yPosition = y;
    target = p;
    submittedFirst = false;
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

  public void setSubmittedFirst(boolean submittedFirst) {
    this.submittedFirst = submittedFirst;
  }

  public boolean wasFirst(){
    return this.submittedFirst;
  };

  @Override
  public String toString() {
    return "Move{" +
            "xPosition=" + xPosition +
            ", yPosition=" + yPosition +
            ", target=" + target +
            ", submittedFirst=" + submittedFirst +
            '}';
  }
}
