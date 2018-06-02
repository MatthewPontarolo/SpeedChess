/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Rook'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Rook extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Point> moves  = new ArrayList<Point>();

	public Rook(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Rook";
	}

	public void setValidMoves(Board board, int x, int y, int playerType)
	{
		moves.clear();
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
				if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0 && board.getPiece(xCoord, yCoord) != null)
        {
          break;
        }
				hBackward++;
			}
	}


	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}


	public void move(int x, int y) {
		this.setPosition(x, y);
	}
}
