/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Pawn'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Pawn extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves;
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
	public void setValidMoves(Board board, int x, int y) {
		// if this is pawn's first move, it can move two squares forward
		if (firstMove == true)
		{
			int X_move = 0;
			int Y_move = 2;
			Point firstMove = new Point(X_move, Y_move);
			moves.add(firstMove);
		}
		else
		{
			// if a piece is occupying a square diagonal from pawn, it can capture
			if (board.getPiece(x++, y++) != null)
			{
				int X_move = x++;
				int Y_move = y++;
				Point move = new Point(X_move, Y_move);
				moves.add(move);
			}
			if (board.getPiece(x--, y++) != null)
			{
				int X_move = x--;
				int Y_move = y++;
				Point move = new Point(X_move, Y_move);
				moves.add(move);
			}
			// if no piece is in front of pawn, it can move forward one square
			if (board.getPiece(x, y++) == null)
			{
				int X_move = x;
				int Y_move = y++;
				Point move = new Point(X_move, Y_move);
				moves.add(move);
			}
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
