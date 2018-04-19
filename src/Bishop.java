/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Bishop'
*/
public class Bishop implements Piece
{
  // Assuming that we are storing board positions as something like 'C2' (??)
  // possibly changes
  int Xposition;
  int Yposition;
  private Arraylist<Integer> moves;
  private boolean alive;

  // CONSTRUCTOR
  public Bishop(int x, int y)
  {
    alive = true;
    this.setPosition(x, y);
  }
  // check if piece is still on board
  public boolean isAlive()
  {
    if (alive == true)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public void setAlive(boolean alive)
  {
    this.alive = alive;
  }
  public void getPosition()
  {

  }

  public void setPosition(int x, int y)
  {

  }
  public int getXPosition()
  {
    return Xposition;
  }
  public int getYPosition()
  {
    return Yposition;
  }

  // Precondition: Piece is on board
  // Postcondition: returns array of viable move options and stores into 'moves' arraylist
  public int validMoves(int position)
  {
  }

  public int move(Board board, int position)
  {
    // check if the position the piece wants to move to is in array of possible moves
    // if yes, update position -- setCurrentPosition(position)
    // if no, give error message -- depending on implementation of UI
    // function could just do nothing


  }
}
