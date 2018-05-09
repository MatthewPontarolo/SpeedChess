/**
* This class implements the Piece interface and defines the specific behavior
*/

import java.util.ArrayList;
import java.awt.Point;

public class King extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	// ??? will change?
	private ArrayList<Point> moves  = new ArrayList<Point>();

	// CONSTRUCTOR
	public King(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "King";
	}

	public void setValidMoves(Board board, int x, int y, int playerType)
	{
		moves.clear();

		//computing the diaglonals for king
		Point move = new Point(x + 1, y + 1);
		moves.add(move);
		Point move1 = new Point(x - 1, y - 1);
		moves.add(move1);
		Point move2 = new Point(x + 1, y - 1);
		moves.add(move2);
		Point move3 = new Point(x - 1, y + 1);
		moves.add(move3);

		//computing the horizontals and verticals
		Point move4 = new Point(x, y + 1);
		moves.add(move4);
		Point move5 = new Point(x, y - 1);
		moves.add(move5);
		Point move6 = new Point(x + 1, y);
		moves.add(move6);
		Point move7 = new Point(x - 1, y);
		moves.add(move7);

	}

	// helper function
	// for UI, call getPossibleMoves
	// returns arrayList of Points for valid moves of current piece position
	// NOTE: will possibly reconsider use of Points bc of return type double
	public ArrayList<Point> getValidMoves(Board board, int playerType) {
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

	public void move(int x, int y) {
		// check if the position the piece wants to move to is in array of possible moves
		// if yes, update position -- setCurrentPosition(position)
		// if no, give error message -- depending on implementation of UI
		// function could just do nothing

		this.setPosition(x, y);

		// no valid moves found
		// error message in UI

	}
}
