/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Rook'
*/
class Rook
{
  // Assuming that we are storing board positions as something like 'C2'
  ENUM currentPosition;
  private Arraylist<Integer> moves;
  boolean alive;

  public Rook(int position)
  {
    alive = true;
    this.setCurrentPosition(position);
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

  public void setAlive()
  {

  }
  public void setCurrentPosition()
  {

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
