import java.util.ArrayList;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];

	public Board(Player player1, Player player2)
	{
	setUp(player1, player2);
	}

	public void setUp(Player player1, Player player2) {
		for (Piece p : player1.getPieces()) {
			addPiece(p);
		}
		for (Piece p : player2.getPieces()) {
			addPiece(p);
		}
	}

	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	// instead of board moving piece, seems to be more logical to make player
	// or gameHost class move the piece instead/store the move for gameTurn
	public void movePiece(Player player, Piece p, int x, int y) {
		// if gameTurn == valid
		spots[p.getXPosition()][p.getYPosition()] = null;
		spots[x][y] = p;
		//p.move(x, y);
		player.movePiece(p, int x, int y);

	}

}
