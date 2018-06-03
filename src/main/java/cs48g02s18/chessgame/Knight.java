package cs48g02s18.chessgame;
/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Knight'
*/


import java.util.ArrayList;
import java.awt.Point;

public class Knight extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves = new ArrayList<Point>();

	/**
	 * Knight setup
	 * @param x		Start x
	 * @param y		Start y
	 */
	public Knight(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	/**
	 * Returns the name
	 * @return "Knight"
	 */
	public String getName() {
		return "Knight";
	}

	/**
	 * Determines which moves are valid
	 * @param board				The game board
	 * @param x					The x pos
	 * @param y					The y pos
	 * @param playerType		The player type (0 or 1)
	 */
	public void setValidMoves(Board board, int x, int y, int playerType) {
		moves.clear();
		if (x + 2 < 8 && y + 1 < 8 && x + 2 >=0 && y + 1 >=0)
		{
			Point move = new Point(x + 2, y + 1);
			if (board.getPiece(x + 2, y + 1) != null)
			{
				if (board.getPiece(x + 2, y + 1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x + 2 < 8 && y - 1 < 8 && x + 2 >= 0 && y - 1 >= 0)
		{
			Point move = new Point(x + 2, y - 1);
			if (board.getPiece(x + 2, y - 1) != null)
			{
				if (board.getPiece(x + 2, y - 1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x - 2 < 8 && y - 1 < 8 && x - 2 >= 0 && y - 1 >= 0)
		{
			Point move = new Point(x - 2, y - 1);
			if (board.getPiece(x - 2, y - 1) != null)
			{
				if (board.getPiece(x - 2, y - 1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x - 2 < 8 && y + 1 < 8 && x - 2 >= 0 && y + 1 >= 0)
		{
			Point move = new Point(x - 2, y + 1);
			if (board.getPiece(x - 2, y + 1) != null)
			{
				if (board.getPiece(x - 2, y + 1).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x + 1 < 8 && y + 2 < 8 && x + 1 >= 0 && y + 2 >= 0)
		{
			Point move = new Point(x + 1, y + 2);
			if (board.getPiece(x + 1, y + 2) != null)
			{
				if (board.getPiece(x + 1, y + 2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x + 1 < 8 && y - 2 < 8 && x + 1 >= 0 && y - 2 >= 0)
		{
			Point move = new Point(x + 1, y - 2);
			if (board.getPiece(x + 1, y - 2) != null)
			{
				if (board.getPiece(x + 1, y - 2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x - 1 < 8 && y + 2 < 8 && x - 1 >= 0 && y + 2 >= 0)
		{
			Point move = new Point(x - 1, y + 2);
			if (board.getPiece(x - 1, y + 2) != null)
			{
				if (board.getPiece(x - 1, y + 2).getPlayer() != this.getPlayer())
				{
					moves.add(move);
				}
			}
			else
			{
				moves.add(move);
			}
		}
		if (x - 1 < 8 && y - 2 < 8 && x - 1 >= 0 && y - 2 >= 0)
		{
			Point move = new Point(x - 1, y - 2);
			if (board.getPiece(x - 1, y - 2) != null)
			{
				if (board.getPiece(x - 1, y - 2).getPlayer() != this.getPlayer())
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
