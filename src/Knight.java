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

	public void setValidMoves(Board board, int x, int y, int playerType) {
		Point move = new Point(2, 1);
		moves.add(move);
		Point move1 = new Point(2, -1);
		moves.add(move1);
		Point move2 = new Point(-2, 1);
		moves.add(move2);
		Point move3 = new Point(-2, -1);
		moves.add(move3);
		Point move4 = new Point(1, 2);
		moves.add(move4);
		Point move5 = new Point(1, -2);
		moves.add(move5);
		Point move6 = new Point (-1, 2);
		moves.add(move6);
		Point move7 = new Point (-1, -2);
		moves.add(move7);
	}

	public ArrayList<Point> getValidMoves(Board board, int playerType) {
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
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
