import java.util.ArrayList;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];
	private Player[] players;
	private int currentPlayer;
	private final static int A = 1;
	private final char horizontal[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	private final int vertical[] = {1, 2, 3, 4, 5, 6, 7, 8};

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

	public void movePiece(Piece p, int x, int y) {
		spots[p.getXPosition()][p.getYPosition()] = null;
		spots[x][y] = p;
	}

	public void addPlayer(Player player, int index) {
		//idk i thought we should use this to keep track of players??
	}

}
