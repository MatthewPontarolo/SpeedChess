package cs48g02s18.chessgame;

public class Move
{
    private int xPosition;
    private int yPosition;
    private Piece target;
    private long time;
    private boolean submittedFirst;

    public Move(Piece p, int x, int y) {
      xPosition = x;
      yPosition = y;
      target = p;
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
      System.out.println("target x position: " + target.getXPosition());
      System.out.println("target y position: " + target.getYPosition());
      System.out.println("xposition: " + xPosition);
      System.out.println("yposition: " + yPosition);
      System.out.println("time: " + time);
      return target.getXPosition() + " "
          + target.getYPosition() + " "
          + xPosition + " "
          + yPosition + " "
          + time;
    }

    public void setSubmittedFirst(boolean submittedFirst) {
        this.submittedFirst = submittedFirst;
    }

    public boolean wasFirst(){
        return this.submittedFirst;
    }
}
