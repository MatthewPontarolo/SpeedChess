package cs48g02s18.chessgame;

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

	/**
	 * Constructs Pawn
	 * @param x
	 * @param y
	 */
	public Pawn(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	/**
	 *
	 * @return "Pawn"
	 */
	public String getName() {
		return "Pawn";
	}

	// must be given a board to check game situation from
	// either have it called explicitly or through helper function

	/**
	 * Sets the valid moves
	 * @param board				The game board
	 * @param x					X pos
	 * @param y					Y pos
	 * @param playerType		0 or 1
	 */
	public void setValidMoves(Board board, int x, int y, int playerType) {
		moves.clear();
		// if this is pawn's first move, it can move two squares forward
		if (firstMove == true)
		{
			// white moves forward with y++
			if(this.getPlayer() == 1)
			{
				Point firstMove = new Point(x + 0, y + 2);
				if (board.getPiece(x, y + 1) == null && board.getPiece(x, y + 2) == null)
				{
						moves.add(firstMove);
				}
			}
			// black moves forward with y--
			else
			{
				Point firstMove = new Point(x + 0, y - 2);
				if (board.getPiece(x, y - 1) == null && board.getPiece(x, y - 2) == null)
				{
						moves.add(firstMove);
				}
			}

		}

		if (this.getPlayer() ==  1)
		{
			// if a piece is occupying a square diagonal from pawn, it can capture
			if (x + 1 < 8 && y + 1 < 8 && x + 1 >= 0 && y + 1 >= 0)
			{
				Point move = new Point(x + 1, y + 1);
				if (board.getPiece(x + 1, y + 1) != null)
				{
					if (board.getPiece(x + 1,y + 1).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
				}
			}
			if (x - 1 < 8 && y + 1 < 8 && x - 1 >= 0 && y + 1 >= 0)
			{
				Point move = new Point(x - 1, y + 1);
				if (board.getPiece(x - 1, y + 1) != null)
				{
					if (board.getPiece(x - 1, y + 1).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
				}
			}
				// if no piece is in front of pawn, it can move forward one square
			if (x < 8 && y + 1 < 8 && x >= 0 && y + 1 >=0)
			{
				Point move = new Point(x + 0, y + 1);
				if (board.getPiece(x, y + 1) == null)
				{
					moves.add(move);
				}
			}
		}
		else
		{
			// if a piece is occupying a square diagonal from pawn, it can capture
			if (x + 1 < 8 && y - 1 < 8 && x + 1 >= 0 && y - 1 >= 0)
			{
				Point move = new Point(x + 1, y - 1);
				if (board.getPiece(x + 1, y - 1) != null)
				{
					if (board.getPiece(x + 1, y - 1).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
				}
			}
			if (x - 1 < 8 && y - 1 < 8 && x - 1 >= 0 && y - 1 >= 0)
			{
				Point move = new Point(x - 1, y - 1);
				if (board.getPiece(x - 1, y - 1) != null)
				{
					if (board.getPiece(x - 1, y - 1).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
				}
			}
				// if no piece is in front of pawn, it can move forward one square
			if (x < 8 && y - 1 < 8 && x >= 0 && y - 1 >=0)
			{
				Point move = new Point(x + 0, y - 1);
				if (board.getPiece(x, y - 1) == null)
				{
						moves.add(move);
				}
			}
		}

	}

	/**
	 *
	 * @param board				Game board
	 * @param playerType		0 or 1
	 * @return moves			List of valid moves
	 */
	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

	/**
	 * Moves according to pawn rules
	 * @param x			Move to X
	 * @param y			Move to Y
	 */
	public void move(int x, int y) {
		if (firstMove == true)
		{
			firstMove = false;
		}

		this.setPosition(x, y);
	}
}
