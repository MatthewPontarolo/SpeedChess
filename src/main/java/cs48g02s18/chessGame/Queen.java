/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Queen'
*/
package speedchess;

import java.util.ArrayList;
import java.awt.Point;

public class Queen extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves  = new ArrayList<Point>();

	public Queen(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Queen";
	}

	public void setValidMoves(Board board, int x, int y, int playerType)
	{
		moves.clear();
		//diagonals - similar to the bishops algoirthm
		// left side bishop -- figure out how to distinguish later
		int xForward = x;
		int yForward = y;
		int xOffset = 0;
		int yOffset = 0;

		// diagonal forward, once anything is in the way, stop
		while (xForward < 8 && yForward < 8 && xForward >= 0 && yForward >=0)
		{
			xOffset++;
			yOffset++;
			int xCoord = x + xOffset;
			int yCoord = y + yOffset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			xForward++;
			yForward++;
		}

		int xBackward = x;
		int yBackward = y;
		// diagonal backwards, once anything is in the way, stop
		xOffset = 0;
		yOffset = 0;
		while (xBackward < 8 && yBackward < 8 && xBackward >= 0 && yBackward >=0)
		{
			xOffset--;
			yOffset--;
			int xCoord = x + xOffset;
			int yCoord = y + yOffset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			xBackward--;
			yBackward--;
		}

		// right side bishop
		xForward = x;
		yForward = y;
		xOffset = 0;
		yOffset = 0;
		// diagonal forward, once anything is in the way, stop
		while (xForward < 8 && yForward < 8 && xForward >= 0 && yForward >= 0)
		{
			xOffset--;
			yOffset++;
			int xCoord = x + xOffset;
			int yCoord = y + yOffset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			xForward--;
			yForward++;
		}

		xBackward = x;
		yBackward = y;
		xOffset = 0;
		yOffset = 0;
		// diagonal backwards, once anything is in the way, stop
		while (xBackward < 8 && yBackward < 8 && xBackward >= 0 && yBackward >= 0)
		{
			xOffset++;
			yOffset--;
			int xCoord = x + xOffset;
			int yCoord = y + yOffset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			xBackward++;
			yBackward--;
		}

		//vertical and horizontal movements - similar to the rook implementation

		// move vertically, if anything blocking, stop immediately
		int vForward = y;
		int vBackward = y;
		int offset = 0;
		while (vForward >= 0 && vForward < 8)
		{
			offset++;
			int xCoord = x;
			int yCoord = y + offset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord,yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			vForward++;
		}
		offset = 0;
		while (vBackward >= 0 && vBackward < 8)
		{
			offset--;
			int xCoord = x;
			int yCoord = y + offset;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord,yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			vBackward--;
		}

		// move horizontally, if anything blocking, stop immediately
		int hForward = x;
		int hBackward = x;
		offset = 0;
		while (hForward >=0 && hForward < 8)
		{
			offset--;
			int xCoord = x + offset;
			int yCoord = y;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord,yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			hForward--;

		}
		offset = 0;
		while (hBackward >= 0 && hBackward < 8)
		{
			offset++;
			int xCoord = x + offset;
			int yCoord = y;
			Point move = new Point(xCoord, yCoord);
			if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
			{
				if (board.getPiece(xCoord, yCoord) != null)
				{
					if (board.getPiece(xCoord,yCoord).getPlayer() != this.getPlayer())
					{
						moves.add(move);
					}
					break;
				}
				moves.add(move);
			}
			hBackward++;
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
