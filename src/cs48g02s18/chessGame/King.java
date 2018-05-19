package cs48g02s18.chessServer.cs48g02s18.chessGame;

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
		if (x+1 < 8 && y + 1 < 8 && x + 1 >= 0 && y+1 >= 0)
		{
			Point move = new Point(x + 1, y + 1);
			if (board.getPiece(x+1,y+1) != null)
			{
				if (board.getPiece(x+1,y+1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x-1 < 8 && y - 1 < 8 && x - 1 >= 0 && y-1 >= 0)
		{
			Point move = new Point(x - 1, y - 1);
			if (board.getPiece(x-1,y-1) != null)
			{
				if (board.getPiece(x-1,y-1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x+1 < 8 && y - 1 < 8 && x + 1 >= 0 && y-1 >= 0)
		{
			Point move = new Point(x + 1, y - 1);
			if (board.getPiece(x+1,y-1) != null)
			{
				if (board.getPiece(x+1,y-1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x-1 < 8 && y + 1 < 8 && x - 1 >= 0 && y+1 >= 0)
		{
			Point move = new Point(x - 1, y + 1);
			if (board.getPiece(x-1,y+1) != null)
			{
				if (board.getPiece(x-1,y+1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}

		//computing the horizontals and verticals
		if (x < 8 && y+1 < 8 && x >= 0 && y+1 >= 0)
		{
			Point move = new Point(x, y + 1);
			if (board.getPiece(x,y+1) != null)
			{
				if (board.getPiece(x,y+1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}

		if (x < 8 && y-1 < 8 && x >= 0 && y-1 >= 0)
		{
			Point move = new Point(x, y - 1);
			if (board.getPiece(x,y-1) != null)
			{
				if (board.getPiece(x,y-1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x+1 < 8 && y < 8 && x+1 >= 0 && y >= 0)
		{
			Point move = new Point(x+1, y);
			if (board.getPiece(x+1,y) != null)
			{
				if (board.getPiece(x+1,y).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x-1 < 8 && y < 8 && x-1 >= 0 && y >= 0)
		{
			Point move = new Point(x-1, y);
			if (board.getPiece(x-1,y) != null)
			{
				if (board.getPiece(x-1,y).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
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
