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
  private ArrayList<Point> moves = new ArrayList<Point>();

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
  public void setValidMoves(Board board, int x, int y, int playerType) {
    //all posible moves up toward the left
    // all possible moves from spot
      // check if occupied, if yes don't add, if no, add

    // left side bishop -- figure out how to distinguish later
    int xForward = x+1;
    int yForward = y+1;
    // diagonal forward, once anything is in the way, stop
    while (xForward <=8 && yForward <= 8 && xForward >= 0 && yForward >=0)
    {
        Point move = new Point(xForward, yForward);
        moves.add(move);
        xForward++;
        yForward++;
        System.out.println("left-forward");
    }

    int xBackward = x-1;
    int yBackward = y-1;
    // diagonal backwards, once anything is in the way, stop
    while (xBackward<=8 && yBackward<= 8 && xBackward >= 0 && yBackward >=0)
    {
        Point move = new Point(xBackward, yBackward);
        moves.add(move);
        xBackward--;
        yBackward--;
        System.out.println("left-backward");

    }

    // right side bishop
    xForward = x-1;
    yForward = y+1;
    // diagonal forward, once anything is in the way, stop
    while (xForward <= 8 && yForward <= 8 && xForward >= 0 && yForward >= 0)
    {

        Point move = new Point(xForward, yForward);
        moves.add(move);
        xForward--;
        yForward++;
        System.out.println("right-forward");


    }

    xBackward = x+1;
    yBackward = y-1;
    // diagonal backwards, once anything is in the way, stop
    while (xBackward <=8 && yBackward <= 8 && xBackward >= 0 && yBackward >=0)
    {

        Point move = new Point(xBackward, yBackward);
        moves.add(move);
        xBackward++;
        yBackward--;
        System.out.println("right-backward");
    }
	}

	// for UI, call selectedPiece.getValidMoves(gameBoard)
	// returns arrayList of Points for valid moves of current piece position
  // NOTE: will possibly reconsider use of Points bc of return type double
	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{
		this.setValidMoves(board, Xposition, Yposition, playerType);
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
