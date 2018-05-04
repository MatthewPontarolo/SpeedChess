/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Pawn'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Pawn extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves = new ArrayList<Point>();
	private boolean firstMove = true;

	public Pawn(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Pawn";
	}

	// must be given a board to check game situation from
	// either have it called explicitly or through helper function

	public void setValidMoves(Board board, int x, int y, int playerType) {
		// if this is pawn's first move, it can move two squares forward
		Piece nullPiece = null;
		if (firstMove == true)
		{
			Point firstMove = new Point(0, 2);
			moves.add(firstMove);
		}

		// if a piece is occupying a square diagonal from pawn, it can capture
		if (x+1 <=8 && y+1 <= 8 && x+1 >= 0 && y+1 >=0)
		{
			Point move = new Point(1, 1);
			moves.add(move);
		}
		if (x-1 <=8 && y+1 <= 8 && x-1 >= 0 && y+1 >=0)
		{
			Point move = new Point(-1, 1);
			moves.add(move);
		}

			// if no piece is in front of pawn, it can move forward one square
		if (x <=8 && y+1 <= 8 && x >= 0 && y+1 >=0)
		{
			Point move = new Point(0, 1);
			moves.add(move);
		}


	}


	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{

		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

	public int move(Board board, int position) {
		// check if the position the piece wants to move to is in array of possible moves
		// if yes, update position -- setCurrentPosition(position)
		// if no, give error message -- depending on implementation of UI
		// function could just do nothing
		if (firstMove == true)
		{
			firstMove = false;
		}

		// no valid moves found
		// error message in UI
		return 0;
	}
}
