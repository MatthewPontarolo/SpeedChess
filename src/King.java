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

	public void setValidMoves(Board board, int x, int y) {
		for (int i = 0; i < x+1; i++)
		{
			for (int j = 0; j < y+1; j++)
			{
				if(i != x && j != y && i >= 0 && j >= 0 && i < 8 && j < 8)
				{
					Point move = new Point(i, j);
					moves.add(move);
				}
			}
		}
	}

	public ArrayList<Point> getValidMoves(Board board) {
		ArrayList<Point> valid = new ArrayList<Point>();
		//code
		return valid;
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
