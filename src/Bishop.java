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
  public Bishop(int x, int y) {
    this.setAlive(true);
    this.setPosition(x, y);
  }

  public String getName() {
  	return "Bishop";
  }

  // Precondition: Piece is on board. get the position of current spot
  // Postcondition: sets array of viable move options and stores into 'moves' arraylist
  public void setValidMoves(Board board, int x, int y) {
    //all posible moves up toward the left
    // all possible moves from spot
      // check if occupied, if yes don't add, if no, add
    for (int j = y-1, i = x-1; j > -1 && i > -1; j--, i--) {
      Point coords = new Point(i, j);
    }
    // left side bishop -- figure out how to distinguish later
    int xForward = x++;
    int yForward = y++;
    // diagonal forward, once anything is in the way, stop
    while ((board.getPiece(xForward, yForward) == null))
    {
      Point move = new Point(xForward, yForward);
      moves.add(move);
      xForward++;
      yForward++;
    }

    int xBackward = x--;
    int yBackward = y--;
    // diagonal backwards, once anything is in the way, stop
    while (board.getPiece(xBackward, yBackward) == null)
    {
      Point move = new Point(xBackward, yBackward);
      moves.add(move);
      xBackward--;
      yBackward--;
    }

    // right side bishop
    xForward = x--;
    yForward = y++;
    // diagonal forward, once anything is in the way, stop
    while ((board.getPiece(xForward, yForward) == null))
    {
      Point move = new Point(xForward, yForward);
      moves.add(move);
      xForward--;
      yForward++;
    }

    xBackward = x++;
    yBackward = y--;
    // diagonal backwards, once anything is in the way, stop
    while (board.getPiece(xBackward, yBackward) == null)
    {
      Point move = new Point(xBackward, yBackward);
      moves.add(move);
      xBackward++;
      yBackward--;
    }
	}

	// for UI, call selectedPiece.getValidMoves(gameBoard)
	// returns arrayList of Points for valid moves of current piece position
  // NOTE: will possibly reconsider use of Points bc of return type double
	public ArrayList<Point> getValidMoves(Board board)
	{
		this.setValidMoves(board, Xposition, Yposition);
		return moves;
	}

  public int move(Board board, Point position) {
    // check if the position the piece wants to move to is in array of possible moves
    // if yes, update position -- setCurrentPosition(position)
    // if no, give error message -- depending on implementation of UI
    // function could just do nothing

    // no valid moves found
    // error message in UI

	return 0;
  }

}
