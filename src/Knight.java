/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Knight'
*/
public class Knight extends Piece
{
  // Assuming that we are storing board positions as something like 'C2'
  private ArrayList<Integer> moves;

  public Knight(int x, int y)
  {
    this.setAlive(true);
    this.setPosition(x, y);
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
