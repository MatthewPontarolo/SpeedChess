package cs48g02s18.chessgame;
/**
* This class implements the Piece interface and defines the specific behavior
*/

import java.util.ArrayList;
import java.awt.Point;

public class King extends Piece {

	private ArrayList<Point> moves  = new ArrayList<Point>();

	/**
	 * King setup
	 * @param x		Start x
	 * @param y		Start y
	 */
	public King(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	/**
	 * Returns the name
	 * @return "King"
	 */
	public String getName() {
		return "King";
	}

	/**
	 * Determines which moves are valid
	 * @param board				The game board
	 * @param x					The x pos
	 * @param y					The y pos
	 * @param playerType		The player type (0 or 1)
	 */
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

	/**
	 * Returns the list of valid moves
	 * @param board             The game board
	 * @param playerType        The playerType (0 or 1)
	 * @return moves            The list of valid moves
	 */
	public ArrayList<Point> getValidMoves(Board board, int playerType) {
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

}
