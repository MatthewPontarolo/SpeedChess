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
		//if (playerType == 0)
		//{
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
				moves.add(move);
				if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0 && board.getPiece(xCoord, yCoord) != null)
        {
          break;
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
				moves.add(move);
				if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0 && board.getPiece(xCoord, yCoord) != null)
        {
          break;
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
				moves.add(move);
				if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0 && board.getPiece(xCoord, yCoord) != null)
        {
          break;
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
				moves.add(move);
				if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0 && board.getPiece(xCoord, yCoord) != null)
        {
          break;
        }
				hBackward++;
			}


		//}
		//else if (playerType == 1)
		//{
			// need to reconsider bc of d/f board perspective
		//}

	}

	// helper function
	// for UI, call getPossibleMoves
	// returns arrayList of Points for valid moves of current piece position
	// NOTE: will possibly reconsider use of Points bc of return type double
	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{
		this.setValidMoves(board, Xposition, Yposition, playerType);
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
