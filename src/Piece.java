import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
  protected int Xposition;
  protected int Yposition;
  protected boolean status;

  // check if piece is still on board
  public boolean isAlive()
  {
    return status;
  }

  public void setAlive(boolean alive)
  {
    status = alive;
  }

  /** SUBJECT TO CHANGE DEPENDING ON HOW WE DECIDE TO STORE BOARD POSITIONS **/
  public void setPosition(int x, int y) {
    Xposition = x;
    Yposition = y;
  }

  public int getXPosition()
  {
    return Xposition;
  }

  public int getYPosition()
  {
    return Yposition;
  }

  public ArrayList<Point> getValidMoves();
}
