package cs48g02s18.chessgame;

public class Move
{
  private int xPosition;
  private int yPosition;
  private Piece target;
  private double time;

  /**
	 * constructor to create the move
   * @param Piece piece chosen
   * @param int x and y coordinates to move
   * @param double timestamp of the move selected
	 */
  public Move(Piece p, int x, int y, double time)
  {
    xPosition = x;
    yPosition = y;
    target = p;
  }

  /**
   * @return the target piece chosen
   */
  public Piece getTargetPiece()
  {
    return target;
  }

  /**
	 * @return the x coordinate of the move selected
	 */
  public int getXMove()
  {
    return xPosition;
  }

  /**
	 * @return the y coordinate of the move selected
	 */
  public int getYMove()
  {
    return yPosition;
  }

  /**
	 * @return x coordinate of the initial position of the piece
	 */
  public int getInitX()
  {
    return target.getXPosition();
  }

  /**
	 * @return y coordinate of the intiial position of the piece
	 */
  public int getInitY()
  {
    return target.getYPosition();
  }

  // Server's timer timestamp
  /**
   * @return the timestamp of the move selected
   */
  public double getTime()
  {
    return time;
  }
}
