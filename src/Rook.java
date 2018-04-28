/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Rook'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Rook extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves;

	public Rook(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Rook";
	}

	public void setValidMoves(Board board, int x, int y)
	{
		// move vertically, if anything blocking, stop immediately
		int vForward = y++;
		int vBackward = y--;
		while (board.getPiece(x, vForward) == null)
		{
			Point move = new Point(x, vForward);
			moves.add(move);
			vForward++;

		}
		while (board.getPiece(x, vBackward) == null)
		{
			Point move = new Point(x, vBackward);
			moves.add(move);
			vBackward--;
		}

		// move horizontally, if anything blocking, stop immediately
		int hForward = x++;
		int hBackward = x--;
		while (board.getPiece(hForward, y) == null)
		{
			Point move = new Point(hForward, y);
			moves.add(move);
			hForward++;

		}
		while (board.getPiece(hBackward, y) == null)
		{
			Point move = new Point(hBackward, y);
			moves.add(move);
			hBackward++;
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

		// no valid moves found
		// error message in UI

		return 0;
	}
}
