/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Knight'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Knight extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves = new ArrayList<Point>();

	public Knight(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Knight";
	}

	public void setValidMoves(Board board, int x, int y) {
		int vForward = y++;
		int vBackward = y--;
		while (board.getPiece(x, vForward) == null)
		{
			
	}

	public ArrayList<Point> getValidMoves(Board board) {
		ArrayList<Point> valid = new ArrayList<Point>();
		//code
		return valid;
	}

	public int move(Board board, Point position) {
		// check if the position the piece wants to move to is in array of possible moves
		// if yes, update position -- setPosition(position)
		// if no, give error message -- depending on implementation of UI
		// function could just do nothing

		// no valid moves found
		// error message in UI

		return 0;
	}
}
