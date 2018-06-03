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

	public Knight(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Knight";
	}

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

	public ArrayList<Point> getValidMoves(Board board, int playerType) {
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

	public void move(int x, int y) {
		this.setPosition(x, y);
	}
}
