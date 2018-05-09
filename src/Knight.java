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
		moves.clear();
		if (x+2 <= 8 && y+1 <= 8 && x+2 >=0 && y+1 >=0)
		{
			if (board.getPiece(x+2,y+1) != null)
			{
				Point move = new Point(x + 2, y + 1);
				if (board.getPiece(x+2,y+1) != null)
				{
					if (board.getPiece(x+2,y+1).getPlayer() != this.getPlayer())
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
		if (x+2 <= 8 && y-1 <= 8 && x+2 >=0 && y-1 >=0)
		{
			if (board.getPiece(x+2,y-1) != null)
			{
				Point move = new Point(x + 2, y - 1);
				if (board.getPiece(x+2,y-1) != null)
				{
					if (board.getPiece(x+2,y-1).getPlayer() != this.getPlayer())
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
		if (x-2 <= 8 && y-1 <= 8 && x-2 >= 0 && y-1 >= 0)
		{
			if (board.getPiece(x-2,y-1) != null)
			{
				Point move = new Point(x - 2, y - 1);
				if (board.getPiece(x-2,y-1) != null)
				{
					if (board.getPiece(x-2,y-1).getPlayer() != this.getPlayer())
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
		if (x+1 <= 8 && y+2 <= 8 && x+1 >= 0 && y+2 >= 0)
		{
			if (board.getPiece(x-2,y-1) != null)
			{
				Point move = new Point(x + 1, y + 2);
				if (board.getPiece(x+1,y+2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
		}
		if (x+1 <= 8 && y-2 <= 8 && x+1 >= 0 && y-2 >= 0)
		{
			if (board.getPiece(x+1,y-2) != null)
			{
				Point move = new Point(x+1,y-2);
				if (board.getPiece(x+1,y-2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
		}
		if (x-1 <= 8 && y+2 <= 8 && x-1 >= 0 && y+2 >= 0)
		{
			if (board.getPiece(x-1,y+2) != null)
			{
				Point move = new Point(x-1,y+2);
				if (board.getPiece(x-1,y+2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
		}
		if (x-1 <= 8 && y-2 <=8 && x-1 >= 0 && y-2 >= 0)
		{
			if (board.getPiece(x-1, y-2) != null)
			{
				Point move = new Point(x-1,y-2);
				if (board.getPiece(x-1,y-2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
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
		// if yes, update position -- setPosition(position)
		// if no, give error message -- depending on implementation of UI
		// function could just do nothing

		this.setPosition(x, y);

		// no valid moves found
		// error message in UI


	}
}
