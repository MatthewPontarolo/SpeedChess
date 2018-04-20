/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Bishop'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Bishop extends Piece
{
  // Assuming that we are storing board positions as something like 'C2' (??)
  // possibly changes
  private ArrayList<Point> moves;

  // CONSTRUCTOR
  public Bishop(int x, int y)
  {
    this.setAlive(true);
    this.setPosition(x, y);
  }

  // Precondition: Piece is on board. get the position of current spot
  // Postcondition: returns array of viable move options and stores into 'moves' arraylist
  public void setValidMoves(int x, int y) {
    //all posible moves up toward the left
    for (int j = y-1, i = x-1; j > -1 && i > -1; j--, i--) {
      Point coords = new Point(i, j);
    }
  }

  public ArrayList<Point> getValidMoves() {
  	ArrayList<Point> valid = new ArrayList<Point>();
  	//code
  	return valid;
  }

  public int move(Board board, int position) {
    // check if the position the piece wants to move to is in array of possible moves
    // if yes, update position -- setCurrentPosition(position)
    // if no, give error message -- depending on implementation of UI
    // function could just do nothing
	return 0;
  }
}
