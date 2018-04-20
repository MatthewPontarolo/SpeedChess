/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Queen'
*/
import java.util.ArrayList;
import java.awt.Point;

public class Queen extends Piece {
	// Assuming that we are storing board positions as something like 'C2'
	private ArrayList<Integer> moves;

	public Queen(int x, int y) {
		this.setAlive(true);
		this.setPosition(x, y);
	}

	public String getName() {
		return "Queen";
	}

	public ArrayList<Point> getValidMoves() {
		ArrayList<Point> valid = new ArrayList<Point>();
		//code
		return valid;
	}

	public int move(Board board, int position) {
		// check if the position the piece wants to move to is in array of possible moves
		// if yes, update position -- setCurrentPosition(position)
		// if no, give error message -- depending on implementation of UI
		// function could just do nothing

		return 0;
	}
}
